package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.BetID;

import java.util.HashSet;
import java.util.Set;

public class Cashier implements ICashier {
    private Set<IPlayerCard> playercards;


    public Cashier(){
        this.playercards = new HashSet<>();

    }

    @Override
    public IPlayerCard distributeGamblerCard() {
        IPlayerCard newCard = new PlayerCard();
        playercards.add(newCard);
        return newCard;
    }

    @Override
    public void returnGamblerCard(IPlayerCard card) {
        playercards.remove(card);
    }

    @Override
    public boolean checkIfBetIsValid(IPlayerCard card, Bet betToCheck) throws BetNotExceptedException {
        for(BetID betID:card.returnBetIDs()){
            if(betToCheck.getBetID().equals(betID)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addAmount(IPlayerCard card, MoneyAmount amount) {
        ((PlayerCard)card).setBalance(new MoneyAmount(((PlayerCard)card).getBalance().getAmountInCents() + amount.getAmountInCents()));
    }
}
