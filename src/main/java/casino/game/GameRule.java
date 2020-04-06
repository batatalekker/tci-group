package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;

import java.util.Set;

public class GameRule implements IGameRule {
    @Override
    public BetResult determineWinner(Integer randomWinValue, Set<Bet> bets) {
        return null;
    }

    @Override
    public int getMaxBetsPerRound() {
        return 0;
    }
}
