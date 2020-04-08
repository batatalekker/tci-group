package casino.gamingmachine;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.IPlayerCard;
import casino.game.BettingRound;
import casino.idfactory.BetID;
import casino.idfactory.GamingMachineID;
import casino.idfactory.IDFactory;

import java.util.HashSet;
import java.util.Set;

public class GamingMachine implements IGamingMachine {

    private GamingMachineID gamingMachineID;
    private Cashier cashier;

    public IPlayerCard getCurrentPlayer() {
        return currentPlayer;
    }

    private IPlayerCard currentPlayer;

    public Set<Bet> getBets() {
        return this.bettingRound.getAllBetsMade();
    }

    private Set<Bet> bets;
    private BettingRound bettingRound;

    public GamingMachine(Cashier cashier){
        this.gamingMachineID = new GamingMachineID(IDFactory.GenerateID("GamingMachine"));
        this.currentPlayer = null;
        bets = new HashSet<>();
        this.cashier = cashier;
    }

    public BettingRound getBettingRound() {
        return bettingRound;
    }

    public void setBettingRound(BettingRound bettingRound) {
        this.bettingRound = bettingRound;
    }

    @Override
    public boolean placeBet(long amountInCents) throws NoPlayerCardException {
        this.bettingRound = new BettingRound();
        Bet newBet = new Bet(new BetID(IDFactory.GenerateID("Bet")),new MoneyAmount(amountInCents));
        this.bettingRound.placeBet(newBet);
        return false;
    }

    //Remade by Joe
    @Override
    public void acceptWinner(BetResult winResult) {
        Bet winningBet = winResult.getWinningBet();
        Set<BetID> temp = currentPlayer.returnBetIDs();
        for (BetID i: temp ) {
            if (i == winningBet.getBetID()) {
                cashier.addAmount(currentPlayer, winResult.getAmountWon());
            }
        }

        //clear
        currentPlayer = null;
        this.bettingRound = new BettingRound();
    }

    @Override
    public GamingMachineID getGamingMachineID() {

        return this.gamingMachineID;
    }

    @Override
    public void connectCard(IPlayerCard card) {
        this.currentPlayer = card;

    }
}
