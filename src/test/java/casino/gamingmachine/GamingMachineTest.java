package casino.gamingmachine;

import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.IPlayerCard;
import casino.cashier.PlayerCard;
import casino.idfactory.GamingMachineID;
import casino.idfactory.IDFactory;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GamingMachineTest {
    @Test
    public void TestPlaceBet() throws NoPlayerCardException {
        //Arrange
        Cashier cashier = mock(Cashier.class);
        GamingMachine gamingMachine = new GamingMachine(cashier);
        PlayerCard playerCard = mock(PlayerCard.class);
        gamingMachine.connectCard(playerCard);
        when(((PlayerCard)playerCard).getBalance()).thenReturn(new MoneyAmount((150)));
        //Act
        boolean success = gamingMachine.placeBet(100);

        //Assert
        assertTrue(success);
    }

    //Re-done by Joe
    @Test
    public void TestAcceptWinnerShouldClearAllBetsOnMachineAndUpdateBalance() throws NoPlayerCardException {
        //Arrange
        Cashier cashier = mock(Cashier.class);
        GamingMachine gamingMachine = new GamingMachine(cashier);
        BetResult winResult = mock(BetResult.class);
        PlayerCard playerCard = mock(PlayerCard.class);
        gamingMachine.connectCard(playerCard);

        //Act
        gamingMachine.placeBet(100);
        gamingMachine.acceptWinner(winResult);


        //Assert
        assertEquals(0,gamingMachine.getBets().size());
        assertEquals(winResult.getAmountWon(), playerCard.getBalance());
    }

    @Test
    public void connectCardTest() {
        //Arrange
        Cashier cashier = new Cashier();
        GamingMachine gamingMachine = new GamingMachine(cashier);
        IPlayerCard playerCard = mock(IPlayerCard.class);

        //Act
        gamingMachine.connectCard(playerCard);

        //
        assertEquals(gamingMachine.getCurrentPlayer(),playerCard);
    }

}