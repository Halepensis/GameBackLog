package halepensis.dev.gamewebapp.external.rawg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

@Data
public class RawgGame {
    private int id;
    private String slug;
    private String name;
    private String released;
    private boolean tba;

    @JsonProperty("background_image")
    private String backgroundImage;

    private Integer metacritic;

    private int playtime;



}
