package casino.gamingmachine;

public class NoPlayerCardException extends Throwable {
    public NoPlayerCardException(String errMsg){
        super(errMsg);
    }
}
