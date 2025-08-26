package halepensis.dev.gamewebapp.mapper;


import halepensis.dev.gamewebapp.dto.UserGameDTO;
import halepensis.dev.gamewebapp.model.UserGame;


public class UserGameMapper {

    public static UserGameDTO toDTO(UserGame userGame) {
        UserGameDTO userGameDTO = new UserGameDTO();
        userGameDTO.setGameID(userGame.getId());
        userGameDTO.setGameStatus(userGame.getStatus());
        userGameDTO.setHoursPlayed(userGame.getHoursPlayed());
        userGameDTO.setLastPlayed(userGame.getLastPlayed());
        userGameDTO.setNotes(userGame.getNotes());
        return userGameDTO;
    }

}
