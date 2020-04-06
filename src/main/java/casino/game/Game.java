package casino.game;

import casino.bet.Bet;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Game implements IGame {
    private Set<Bet> BetMade = new HashSet<>();
    private GameRule rule;
    String gameName;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public void startBettingRound() {

    }

    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
        if(((GamingMachine)gamingMachine).getBettingRound().getAllBetsMade().contains(bet)){
            BetMade.add(bet);
            return true;
        }
        return false;
    }

    @Override
    public void determineWinner() {
        rule.determineWinner(new Random().nextInt(rule.getMaxBetsPerRound()),this.BetMade);
    }

    @Override
    public boolean isBettingRoundFinished() {
        return false;
    }
}
