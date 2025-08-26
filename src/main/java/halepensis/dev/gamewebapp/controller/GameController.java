package halepensis.dev.gamewebapp.controller;

import halepensis.dev.gamewebapp.external.rawg.dto.RawgGame;
import halepensis.dev.gamewebapp.model.Game;
import halepensis.dev.gamewebapp.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/games")
public class GameController {

    private final GameService service;

    @GetMapping
    public List<Game> getAll() {
        return service.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return ResponseEntity.of(service.getGameById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RawgGame>> getExternalGameByName(@RequestParam String title) {
        return ResponseEntity.ok(service.getExternalGameByName(title));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Game> getExternalGameById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getExternalGameById(id));
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game newGame = service.createGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        return ResponseEntity.of(service.updateGame(id, game));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable Long id) {
        if (!service.deleteGameById(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();

    }


}
