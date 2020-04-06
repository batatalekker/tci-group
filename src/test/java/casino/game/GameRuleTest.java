package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameRuleTest {
    int maxBetsPerRound;


    @Test
    public void determineWinner() {
        //arrange
        GameRule gameRule = new GameRule(maxBetsPerRound, 20);
        int randomValue = 4;
        Set<Bet> bets = new HashSet<>();
        for (int i = 0; i < maxBetsPerRound; i++)
        {
            MoneyAmount moneyAmount = mock(MoneyAmount.class);
            Bet bet = mock(Bet.class);
            when(bet.getMoneyAmount()).thenReturn(moneyAmount);
            bets.add(bet);
        }
        //act
        BetResult betResult = gameRule.determineWinner(randomValue,bets);

        //assert
        assertNotNull(betResult);
    }

    @Test
    public void getMaxBetsPerRound() {
        //arrange
        IGameRule gameRule = new GameRule(maxBetsPerRound,10);

        //act
        int max = gameRule.getMaxBetsPerRound();

        //assert
        assertEquals(maxBetsPerRound,max);
    }
}