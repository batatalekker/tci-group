package casino.game;

import bettingauthoritiyAPI.BetToken;
import casino.bet.Bet;
import casino.idfactory.BettingRoundID;
import casino.idfactory.IDFactory;

import java.util.HashSet;
import java.util.Set;

public class BettingRound implements IBettingRound {
    private BettingRoundID bettingRoundID;
    Set<Bet> bets;
    private BetToken betToken;

    public BettingRound(){
        this.bettingRoundID = new BettingRoundID(IDFactory.GenerateID("BettingRound"));
        bets = new HashSet<>();
        this.betToken = new BetToken(bettingRoundID);
    }

    @Override
    public BettingRoundID getBettingRoundID() {

        return this.bettingRoundID;
    }

    @Override
    public boolean placeBet(Bet bet) {
        if(!this.bets.contains(bet)){
            bets.add(bet);
            return true;
        }
        return false;
    }

    @Override
    public Set<Bet> getAllBetsMade() {
        return this.bets;
    }

    @Override
    public BetToken getBetToken() {
        return this.betToken;
    }

    @Override
    public int numberOFBetsMade() {
        return this.bets.size();
    }
}
