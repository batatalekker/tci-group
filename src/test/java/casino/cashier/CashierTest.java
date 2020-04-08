package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.BetID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnitParamsRunner.class)
public class CashierTest {

    private static final MoneyAmount VALID_MONEY = new MoneyAmount(500);
    private static final BetID VALID_BETID = new BetID("12345");
    private static final MoneyAmount INVALID_MONEY = new MoneyAmount(-50);

    private static final Object[] getInvalidMoney() {
        return new Object[] [] {{ new MoneyAmount(-50) }};
    }

    private static final Object[] getValidMoney() {
        return new Object[] [] {{new MoneyAmount( 300)}};
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

    //Implemented by Joe
    //Tests indirect output
    @Test
    public void returnGamblerCardShouldLeaveCardWithNoMoney() {
        //arrange
        Cashier cashier = new Cashier();
        PlayerCard playerCard = (PlayerCard) cashier.distributeGamblerCard();
        playerCard.setBalance(new MoneyAmount(200));

        //act
        cashier.returnGamblerCard(playerCard);

        //assert
        assertEquals(0, playerCard.getBalance().getAmountInCents());
    }

    //Implemented by Joe
    //
    @Test(expected = BetNotExceptedException.class)
    @Parameters(method = "getInvalidMoney")
    public void checkIfBetIsValidShouldThrowExceptionWhenInvalidBet(MoneyAmount invalidMoneyAmountOnCard) throws BetNotExceptedException {
        //arrange
        Cashier cashier = new Cashier();
        PlayerCard card = new PlayerCard();
        card.setBalance(invalidMoneyAmountOnCard);
        Bet bet = new Bet(VALID_BETID, VALID_MONEY);

        //act
        cashier.checkIfBetIsValid(card, bet);

        //assert

    }

    @Test
    public void checkIfBetIsValidShouldSubstractAmountFromCardIfSuccesfull() {

    }


    //Implemented by Joe
    //validates indirect output
    @Test
    @Parameters(method = "getValidMoney")
    public void addAmountShouldAddAmountToCardBalance(MoneyAmount moneyAmount) {
        //arrange
        Cashier cashier = new Cashier();
        PlayerCard playerCard = (PlayerCard) cashier.distributeGamblerCard();

        //act
        cashier.addAmount(playerCard, moneyAmount);

        //assert
        assertEquals(moneyAmount.getAmountInCents(), playerCard.getBalance().getAmountInCents());
    }
}