package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.BetID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class CashierTest {

    private static final MoneyAmount VALID_MONEY = new MoneyAmount(500);
    private static final BetID VALID_BETID = new BetID("12345");
    private static final MoneyAmount INVALID_MONEY = new MoneyAmount(-50);

    private static final Object[] getInvalidMoney() {
        return new Object[] [] {{ new MoneyAmount(-50) }};
    }

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

    @Test(expected = BetNotExceptedException.class)
    @Parameters(method = "getInvalidMoney")
    public void checkIfBetIsValidShouldThrowExceptionWhenInvalidBet(MoneyAmount invalidMoneyAmountOnCard) throws BetNotExceptedException {
        //arrange
        Cashier cashier = new Cashier();
        PlayerCard card = mock(PlayerCard.class);
        card.setBalance(invalidMoneyAmountOnCard);
        Bet bet = new Bet(VALID_BETID, VALID_MONEY);

        //act
        cashier.checkIfBetIsValid(card, bet);

        //assert

    }

    @Test
    public void checkIfBetIsValidShouldSubstractAmountFromCardIfSuccesfull() {

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