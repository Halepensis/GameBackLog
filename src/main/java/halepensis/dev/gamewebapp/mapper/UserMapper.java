package halepensis.dev.gamewebapp.mapper;

import halepensis.dev.gamewebapp.dto.UserDTO;
import halepensis.dev.gamewebapp.model.User;
import halepensis.dev.gamewebapp.model.UserGame;


import java.util.stream.Collectors;


public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getName());
        userDTO.setRegistrationDate(user.getRegistrationDate());
        userDTO.setGamesList(
                user.getGames().stream()
                        .map(UserGame::getGame)
                        .collect(Collectors.toList())


        );
        return userDTO;

    }

    public static User toModel(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getUsername());
        user.setPassword(user.getPassword());
        return user;
    }
}
