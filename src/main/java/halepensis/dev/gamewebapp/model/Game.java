package halepensis.dev.gamewebapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "games",
        uniqueConstraints ={
        @UniqueConstraint(columnNames = {"title","release_date"})
        })

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    private String externalSource; // e.g. "rawg", "igdb", etc.
    @Column(nullable = false)
    private String title;
    private String description;
    private String imageUrl;
    private List<String> platform;
    private Integer metacritic;
    private Date releaseDate;




}
