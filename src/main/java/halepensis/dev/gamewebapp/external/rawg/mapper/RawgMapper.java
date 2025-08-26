package halepensis.dev.gamewebapp.external.rawg.mapper;

import halepensis.dev.gamewebapp.external.rawg.dto.RawgGameDetails;
import halepensis.dev.gamewebapp.model.Game;
import lombok.Data;


@Data
public class RawgMapper {


    public static Game toGame(RawgGameDetails rawgGame) {
        Game game = new Game();
        game.setExternalId(Long.valueOf(rawgGame.getId()));
        game.setExternalSource("rawg");
        game.setTitle(rawgGame.getName());
        game.setDescription(rawgGame.getDescription());
        game.setImageUrl(rawgGame.getBackgroundImage());
        game.setDescription(rawgGame.getDescription());
        game.setMetacritic(Integer.valueOf(rawgGame.getMetacritic()));
        game.setReleaseDate(rawgGame.getReleased());
        game.setPlatform(rawgGame.getPlatforms().stream()
                .map(platform -> platform.getPlatform().getName())
                .toList());

        return game;


    }
}
