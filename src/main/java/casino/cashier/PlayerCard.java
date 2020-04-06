package casino.cashier;

import casino.bet.MoneyAmount;
import casino.idfactory.BetID;
import casino.idfactory.CardID;
import casino.idfactory.IDFactory;

import java.util.HashSet;
import java.util.Set;

public class PlayerCard implements IPlayerCard {
    private Set<BetID> betIds;
    private CardID cardID;
    private MoneyAmount balance;

    public PlayerCard(){
        betIds = new HashSet<>();
        this.cardID = new CardID(IDFactory.GenerateID("PlayerCard"));
        balance = new MoneyAmount(0);
    }

    public MoneyAmount getBalance() {
        return balance;
    }

    public void setBalance(MoneyAmount balance) {
        this.balance = balance;
    }

    @Override
    public Set<BetID> returnBetIDs() {
        return this.betIds;
    }

    @Override
    public Set<BetID> returnBetIDsAndClearCard() {
        Set<BetID> temp=new HashSet<>();
        for(BetID id:this.betIds){
            temp.add(id);
        }
        this.betIds=new HashSet<>();
        return temp;
    }

    @Override
    public BetID generateNewBetID() {
        BetID newId = new BetID(IDFactory.GenerateID("Bet"));
        return newId;
    }

    @Override
    public int getNumberOfBetIDs() {
        return this.betIds.size();
    }

    @Override
    public CardID getCardID() {
        return this.cardID;
    }
}
