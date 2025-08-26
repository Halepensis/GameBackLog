package halepensis.dev.gamewebapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;
import java.time.LocalDateTime;


//Relacion que tiene el usuario con el juego concreto

@Entity
@Table(name = "user_games",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_user_game",
                columnNames = {"user_id", "game_id"}))
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Game game;

    @Enumerated
    private GameStatus status;

    private int hoursPlayed;
    private Date lastPlayed;

    private Date dateAdded;

    private String notes;

    @PrePersist
    public void onCreated() {
        dateAdded = Date.valueOf(LocalDateTime.now().toLocalDate());
    }
}
