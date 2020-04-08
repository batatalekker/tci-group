package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.BetID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class CashierTest {

    private static final MoneyAmount BET_COST = new MoneyAmount(100);
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
        Bet bet = new Bet(VALID_BETID, BET_COST);
        cashier.addAmount(card, invalidMoneyAmountOnCard);

        //act
        cashier.checkIfBetIsValid(card, bet);

        //assert

    }

    //Implemented by Joe 08/04/2020
    //
    @Test
    @Parameters(method = "getValidMoney")
    public void checkIfBetIsValidShouldReturnTrueIfBetIsValid(MoneyAmount validMoneyAmount) throws BetNotExceptedException {
        //arrange
        Cashier cashier = new Cashier();
        PlayerCard card = new PlayerCard();
        Bet bet = new Bet(VALID_BETID, BET_COST);
        cashier.addAmount(card, validMoneyAmount);

        //act
        boolean result = cashier.checkIfBetIsValid(card, bet);

        //assert
        assertTrue(result);
        assertEquals((validMoneyAmount.getAmountInCents()- bet.getMoneyAmount().getAmountInCents()), card.getBalance().getAmountInCents());
    }


    //Implemented by Joe 08/04/2020
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