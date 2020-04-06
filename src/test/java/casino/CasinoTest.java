package casino;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.cashier.PlayerCard;
import casino.game.Game;
import casino.idfactory.BetID;
import casino.idfactory.IDFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CasinoTest {

    @Test
    public void addGameShouldAddGameToCasino() {
        //arrange
        String gameName = "Best game";
        Game bestGame = new Game(gameName);
        Casino casino = new Casino();

        //act
        casino.addGame(gameName, bestGame);

        //assert
        Assert.assertEquals(1, casino.games.size());

    }

    @Test
    public void getGameShouldReturnGameFromCasino() {
        //arrange
        Casino casino = new Casino();
        String gameName = "new game";
        Game bestGame = new Game(gameName);

        //act
        casino.addGame(gameName, bestGame);

        //assert
        Assert.assertEquals(bestGame, casino.getGame(gameName));
    }

    @Test
    public void checkIfBetIsValidReturnsTrue() {
        //arrange
        Casino casino = new Casino();
        PlayerCard playerCard = new PlayerCard();
        BetID betID = new BetID("12345");
        MoneyAmount moneyAmount = new MoneyAmount(500);
        Bet bet = new Bet(betID, moneyAmount);



        //act
        boolean result = casino.checkIfBetIsValid(playerCard, bet);

        //assert
        assertNotNull(result);
    }
}