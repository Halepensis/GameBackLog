package halepensis.dev.gamewebapp.dto;

import halepensis.dev.gamewebapp.model.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Data
@NoArgsConstructor
public class UserGameDTO {
    private long gameID;
    private GameStatus gameStatus;
    private int hoursPlayed;
    private Date lastPlayed;
    private String notes;
}
