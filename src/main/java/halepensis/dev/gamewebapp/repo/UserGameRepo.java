package halepensis.dev.gamewebapp.repo;

import halepensis.dev.gamewebapp.dto.UserGameDTO;
import halepensis.dev.gamewebapp.model.Game;
import halepensis.dev.gamewebapp.model.User;
import halepensis.dev.gamewebapp.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameRepo extends JpaRepository<UserGame, Long> {
    boolean existsByUserAndGame(User user, Game game);

    List<UserGame> findALLByUser(User user);

    boolean existsByIdAndUser(long id, User user);
}
