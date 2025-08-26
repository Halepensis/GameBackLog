package halepensis.dev.gamewebapp.service;

import halepensis.dev.gamewebapp.dto.AuthRequest;
import halepensis.dev.gamewebapp.dto.AuthResponse;
import halepensis.dev.gamewebapp.dto.UserDTO;
import halepensis.dev.gamewebapp.mapper.UserMapper;
import halepensis.dev.gamewebapp.model.User;
import halepensis.dev.gamewebapp.repo.UserRepo;
import halepensis.dev.gamewebapp.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepo repo;
    private final PasswordEncoder passEncoder = new BCryptPasswordEncoder();
    private final JwtUtils jwtUtils;


    public List<UserDTO> getUsers() {
        List<User> listUsers = repo.findAll();

        return listUsers.stream()
                .map(UserMapper::toDTO)
                .toList();

    }

    public Optional<UserDTO> getUserById(long id) {
        return repo.findById(id)
                .map(UserMapper::toDTO);
    }

    public UserDTO addUser(User user) {
        user.setPassword(passEncoder.encode(user.getPassword()));
        return UserMapper.toDTO(repo.save(user));
    }

    public ResponseEntity<AuthResponse> loginUser(AuthRequest user) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        if (!auth.isAuthenticated()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        AuthResponse token = new AuthResponse(jwtUtils.generateJwtToken((UserDetails) auth.getPrincipal()));
        return ResponseEntity.ok(token);
    }

    public Optional<UserDTO> updateUser(long id, User newUser) {
        return repo.findById(id)
                .map(user ->
                {
                    user.setId(newUser.getId());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return UserMapper.toDTO(repo.save(user));
                });
    }

    public boolean deleteUser(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        ;
        return false;
    }

}
