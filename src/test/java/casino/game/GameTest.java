package casino.game;

import bettingauthoritiyAPI.BettingAuthority;
import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;
import casino.gamingmachine.NoPlayerCardException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class GameTest {
    GameRule rule;
    BettingAuthority bettingAuthority;

    @Before
    public void before(){
        rule = mock(GameRule.class);
        bettingAuthority = new BettingAuthority();
    }

    @Test
    public void TestAcceptBet() throws NoCurrentRoundException, NoPlayerCardException {
        //arrange
        Game game = new Game();
        Bet bet = mock(Bet.class);
        MoneyAmount moneyAmount = mock(MoneyAmount.class);
        GamingMachine gamingMachine = mock(GamingMachine.class);

        //act
        game.startBettingRound();
        when(bet.getMoneyAmount()).thenReturn(moneyAmount);
        when(gamingMachine.placeBet(bet.getMoneyAmount().getAmountInCents())).thenReturn(true);
        boolean isAccepted = game.acceptBet(bet,gamingMachine);

        //assert
        assertTrue(isAccepted);
    }

    @Test
    public void acceptBetTest_WhenGamingMachineFailedPlacingBet() throws NoPlayerCardException, NoCurrentRoundException{
        //arrange
        Game game = new Game();
        Bet bet = mock(Bet.class);
        MoneyAmount moneyAmount = mock(MoneyAmount.class);
        GamingMachine gamingMachine = mock(GamingMachine.class);

        //act
        game.startBettingRound();
        when(bet.getMoneyAmount()).thenReturn(moneyAmount);
        when(gamingMachine.placeBet(bet.getMoneyAmount().getAmountInCents())).thenReturn(false);
        boolean isAccepted = game.acceptBet(bet,gamingMachine);

        //assert
        assertFalse(isAccepted);
    }
}