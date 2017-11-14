package pdg5.common.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxime Guillod
 */
public class Load extends Message {

    /**
     * List of available games a player have (even finished ones)
     */
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

    public List<Game> getGames() {
        return games;
    }

}
