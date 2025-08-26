package halepensis.dev.gamewebapp.exception;

public class GameAlreadyAddedException extends RuntimeException {
    public GameAlreadyAddedException(String message) {
        super(message);
    }
}
