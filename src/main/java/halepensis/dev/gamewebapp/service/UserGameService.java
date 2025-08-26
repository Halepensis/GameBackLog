package halepensis.dev.gamewebapp.service;

import halepensis.dev.gamewebapp.dto.UserGameDTO;
import halepensis.dev.gamewebapp.exception.GameAlreadyAddedException;
import halepensis.dev.gamewebapp.mapper.UserGameMapper;
import halepensis.dev.gamewebapp.model.Game;
import halepensis.dev.gamewebapp.model.GameStatus;
import halepensis.dev.gamewebapp.model.User;
import halepensis.dev.gamewebapp.model.UserGame;
import halepensis.dev.gamewebapp.repo.GameRepo;
import halepensis.dev.gamewebapp.repo.UserGameRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserGameService {
    private final UserGameRepo repo;
    private final GameRepo gameRepo;
    private final CurrentUserService currentUser;

    public UserGameDTO addGame(long newGame) {
        User user = currentUser.getCurrentUser();
        Game game = gameRepo.findById(newGame)
                .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado"));

        if (repo.existsByUserAndGame(user, game)) {
            throw new GameAlreadyAddedException("El juego ya esta asociado al usuario");
        }

        UserGame newUserGame = new UserGame();
        newUserGame.setUser(user);
        newUserGame.setGame(game);
        newUserGame.setStatus(GameStatus.JUGANDO);


        return UserGameMapper.toDTO(repo.save(newUserGame));

    }

    public List<UserGameDTO> getAllGames() {
        User user = currentUser.getCurrentUser();

        return repo.findALLByUser(user)
                .stream()
                .map(UserGameMapper::toDTO)
                .toList();

    }

    public boolean deleteGameById(long id) {
        User user = currentUser.getCurrentUser();
        if (repo.existsByIdAndUser(id, user)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
