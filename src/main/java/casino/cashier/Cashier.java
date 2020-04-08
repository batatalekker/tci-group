package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.BetID;

import java.util.HashSet;
import java.util.Set;

public class Cashier implements ICashier {
    private Set<IPlayerCard> playercards;
    private Set<BetID> betIDS;

    public Set<IPlayerCard> getPlayercards() {
        return playercards;
    }

    public Cashier() {
        this.playercards = new HashSet<>();
        this.betIDS = new HashSet<>();
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
        MoneyAmount cardAmount = ((PlayerCard)card).getBalance();
        MoneyAmount betAmount = betToCheck.getMoneyAmount();

        if (cardAmount.getAmountInCents() < 0) {
            throw new BetNotExceptedException("Invalid amount on card");
        }
        else if (cardAmount.getAmountInCents() < betAmount.getAmountInCents()) {
            throw new BetNotExceptedException("Not enough money on card");
        }
        else {
            ((PlayerCard) card).setBalance(new MoneyAmount(cardAmount.getAmountInCents() - betAmount.getAmountInCents()));
            return true;
        }
    }

    @Override
    public void addAmount(IPlayerCard card, MoneyAmount amount) {
        ((PlayerCard)card).setBalance(new MoneyAmount(((PlayerCard)card).getBalance().getAmountInCents() + amount.getAmountInCents()));
    }
}
