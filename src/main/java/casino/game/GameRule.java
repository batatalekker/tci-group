package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;

import java.util.Set;

public class GameRule implements IGameRule {
    private int maxBetPerRound;
    private MoneyAmount winningAmout;

    public GameRule(int maxperRound, long amountInCent){
        this.maxBetPerRound = maxperRound;
        winningAmout = new MoneyAmount(amountInCent);
    }

    public MoneyAmount getWinningAmout() {
        return winningAmout;
    }

    public void setWinningAmout(MoneyAmount winningAmout) {
        this.winningAmout = winningAmout;
    }

    public void setMaxBetPerRound(int maxBetPerRound) {
        this.maxBetPerRound = maxBetPerRound;
    }

    @Override
    public BetResult determineWinner(Integer randomWinValue, Set<Bet> bets) {
        Bet temp = null;
        int i = 0;
        for(Bet bet:bets){
            if(i==randomWinValue){
                temp = bet;
                return new BetResult(bet,winningAmout);
            }

            i+=1;

        }
        return null;
    }

    @Override
    public int getMaxBetsPerRound() {
        return this.maxBetPerRound;
    }
}
