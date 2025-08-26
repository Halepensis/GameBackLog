package halepensis.dev.gamewebapp.repo;

import halepensis.dev.gamewebapp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    List<Game> findByTitleContaining(String title);
}
