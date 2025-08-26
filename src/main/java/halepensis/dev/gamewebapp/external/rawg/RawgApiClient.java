package halepensis.dev.gamewebapp.external.rawg;

import halepensis.dev.gamewebapp.external.rawg.dto.RawgGame;
import halepensis.dev.gamewebapp.external.rawg.dto.RawgGameDetails;
import halepensis.dev.gamewebapp.external.rawg.dto.RawgGameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawgApiClient {

    private final WebClient webClient;


    @Value("${rawg.api.key}")
    private String apiKey;

    public RawgApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.rawg.io/api").build();
    }

    public List<RawgGame> searchGame(String query) {
        List<RawgGame> games = new ArrayList<>();

        RawgGameResponse res = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games")
                        .queryParam("search", query)
                        .queryParam("key", apiKey)
                        .queryParam("page", 1)
                        .queryParam("page_size", 10)
                        .build())
                .retrieve()
                .bodyToMono(RawgGameResponse.class)
                .block();

        if (res != null) {
            games = res.getResults();
        }
        return games;


    }

    public RawgGameDetails searchGameById(Integer id) {
        RawgGameDetails game = new RawgGameDetails();

        RawgGameDetails res = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games/" + id)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(RawgGameDetails.class)
                .block();

        if (res != null) {
            game = res;
        }
        return game;


    }
}
