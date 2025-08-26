package halepensis.dev.gamewebapp.service;

import halepensis.dev.gamewebapp.external.rawg.RawgApiClient;
import halepensis.dev.gamewebapp.external.rawg.dto.RawgGame;
import halepensis.dev.gamewebapp.external.rawg.dto.RawgGameDetails;
import halepensis.dev.gamewebapp.external.rawg.dto.RawgGameResponse;
import halepensis.dev.gamewebapp.external.rawg.mapper.RawgMapper;
import halepensis.dev.gamewebapp.model.Game;
import halepensis.dev.gamewebapp.repo.GameRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepo repo;
    private final RawgApiClient rawgApiClient;

    public List<Game> getAllGames() {
        return repo.findAll();
    }

    public Optional<Game> getGameById(long id) {
        return repo.findById(id);
    }

    public Game createGame(Game game) {
        return repo.save(game);
    }

    public boolean deleteGameById(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Game> updateGame(long id, Game updatedGame) {
        return repo.findById(id)
                .map(game -> {
                    game.setTitle(updatedGame.getTitle());
                    game.setDescription(updatedGame.getDescription());
                    game.setReleaseDate(updatedGame.getReleaseDate());
                    game.setImageUrl(updatedGame.getImageUrl());
                    game.setMetacritic(updatedGame.getMetacritic());
                    game.setPlatform(updatedGame.getPlatform());
                    return repo.save(game);
                });
    }


    public List<RawgGame> getExternalGameByName(String name) {
        return rawgApiClient.searchGame(name);
//        return repo.findByTitleContaining(name);
    }

    public Game getExternalGameById(Integer id) {
        return RawgMapper.toGame(rawgApiClient.searchGameById(id));
    }
}
