package halepensis.dev.gamewebapp.repo;

import halepensis.dev.gamewebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String username);

    User findByEmail(String username);
}
