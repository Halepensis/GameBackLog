package halepensis.dev.gamewebapp.repo;

import halepensis.dev.gamewebapp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    List<Game> findByTitleContaining(String title);

    Optional<Game> findByExternalId(Long externalId);
}
