package halepensis.dev.gamewebapp.external.rawg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RawgGameDetails {
    private Integer id;
    private String name;
    private String description;
    private Date released;

    @JsonProperty("background_image")
    private String backgroundImage;
    private String website;
    private Integer rating;
    private String ratingTop;
    private String metacritic;
    private Integer playtime;
    private List<RawgPlatforms> platforms;



}
