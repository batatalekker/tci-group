package casino;

import casino.bet.Bet;
import casino.cashier.IPlayerCard;
import casino.game.Game;
import casino.game.IGame;

import java.util.ArrayList;
import java.util.List;

public class Casino implements ICasino{
    ArrayList<Game> games;

    public Casino() {
        games = new ArrayList<Game>();
    }

    @Override
    public void addGame(String gameName, IGame gameToAdd) {
        games.add((Game) gameToAdd);
    }

    @Override
    public IGame getGame(String name) {
        for (Game game :
                games) {
            if (game.getGameName().equals(name)){
                return game;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfBetIsValid(IPlayerCard card, Bet betToCheck) {
        return true;
    }
}
