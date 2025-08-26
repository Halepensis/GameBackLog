package halepensis.dev.gamewebapp;

import halepensis.dev.gamewebapp.model.Game;
import halepensis.dev.gamewebapp.model.GameStatus;
import halepensis.dev.gamewebapp.model.User;
import halepensis.dev.gamewebapp.model.UserGame;
import halepensis.dev.gamewebapp.repo.GameRepo;
import halepensis.dev.gamewebapp.repo.UserGameRepo;
import halepensis.dev.gamewebapp.repo.UserRepo;
import halepensis.dev.gamewebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GameWebAppApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(GameWebAppApplication.class, args);


    }


}
