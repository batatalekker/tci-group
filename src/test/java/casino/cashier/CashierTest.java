package casino.cashier;

import casino.bet.MoneyAmount;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CashierTest {

    @Test
    public void distributeGamblerCard() {
        //arrange
        Cashier cashier = new Cashier();

        //act
        IPlayerCard iPlayerCard = cashier.distributeGamblerCard();

        //assert
        assertNotNull(iPlayerCard);
    }

    @Test
    public void returnGamblerCard() {
        //arrange
        Cashier cashier = new Cashier();
        IPlayerCard iPlayerCard = cashier.distributeGamblerCard();

        //act
        cashier.returnGamblerCard(iPlayerCard);

        //assert
        assertEquals(0, cashier.getPlayercards().size());
    }

    @Test
    public void addAmount() {
        //arrange
        Cashier cashier = new Cashier();
        PlayerCard playerCard = (PlayerCard) cashier.distributeGamblerCard();
        MoneyAmount moneyAmount = new MoneyAmount(500);

        //act
        cashier.addAmount(playerCard, moneyAmount);

        //assert
//        assertEquals(moneyAmount,playerCard.getBalance());
    }
}