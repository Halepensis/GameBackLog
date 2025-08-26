package halepensis.dev.gamewebapp.controller;

import halepensis.dev.gamewebapp.dto.AuthRequest;
import halepensis.dev.gamewebapp.dto.AuthResponse;
import halepensis.dev.gamewebapp.dto.UserDTO;
import halepensis.dev.gamewebapp.model.User;
import halepensis.dev.gamewebapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4321")
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDTO> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        return ResponseEntity.of(service.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        UserDTO newUser = service.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest user) {
        return service.loginUser(user);

    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable long id,
            @RequestBody User user
    ) {
        return ResponseEntity.of(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if (!service.deleteUser(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
