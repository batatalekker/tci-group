package casino.game;

public class NoCurrentRoundException extends Throwable {
    public NoCurrentRoundException(String errMsg){
        super(errMsg);
    }
}
