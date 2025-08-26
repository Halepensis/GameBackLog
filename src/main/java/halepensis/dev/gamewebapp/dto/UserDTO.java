package halepensis.dev.gamewebapp.dto;

import halepensis.dev.gamewebapp.model.Game;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    private List<Game> gamesList;

}
