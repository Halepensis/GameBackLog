package halepensis.dev.gamewebapp.external.rawg.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RawgGameResponse {
    private int count;
    private String next;
    private String previous;
    private List<RawgGame> results;

}
