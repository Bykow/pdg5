package pdg5.common.protocole;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maxime Guillod
 */
public class Load implements Serializable {

   //List of available games a player have (even finished ones)
    private List<Game> games;

    /**
     * Constructor
     * @param games list of available games
     */
    public Load(List<Game> games) {
        this.games = games;
    }

    /**
     * return the list of available games of a player
     * @return the list of available games of a player
     */
    public List<Game> getGames() {
        return games;
    }

}
