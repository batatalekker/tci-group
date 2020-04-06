package casino.cashier;

public class BetNotExceptedException extends Throwable {
    public BetNotExceptedException(String errMsg){
        super(errMsg);
    }
}
