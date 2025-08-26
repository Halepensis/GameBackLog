package halepensis.dev.gamewebapp.controller;

import halepensis.dev.gamewebapp.dto.UserGameDTO;

import halepensis.dev.gamewebapp.service.UserGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usergames")
public class UserGameController {

    private final UserGameService service;

    @GetMapping
    public ResponseEntity<List<UserGameDTO>> getUserGame() {
        return ResponseEntity.ok(service.getAllGames());
    }

    @PostMapping
    public ResponseEntity<UserGameDTO> addGame(@RequestBody long newGameID) {
        return ResponseEntity.ok(service.addGame(newGameID));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserGameDTO> deleteGame(@PathVariable long id) {
        if (!service.deleteGameById(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();

    }


}
