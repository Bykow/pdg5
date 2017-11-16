package pdg5.common.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxime Guillod
 * Class sent through the connection by the server representing
 * the list of available games a player have.
 */
public class Load extends Message {

   //List of available games a player have (even finished ones)
    private List<Game> games;

    /**
     * Constructor
     * @param games list of available games
     */
    public Load(List<Game> games) {
        this.games = games;
    }

    public Load() {
        this.games = new ArrayList<Game>();
    }

    /**
     * return the list of available games of a player
     * @return the list of available games of a player
     */
    public List<Game> getGames() {
        return games;
    }
}
