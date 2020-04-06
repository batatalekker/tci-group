package casino.gamingmachine;

public class NoPlayerCardException extends Exception {
    public NoPlayerCardException(String errMsg){
        super(errMsg);
    }
}
