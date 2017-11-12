package pdg5.common.protocole;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class Game implements Serializable {

    private int gameId;

    /**
     * Constructor 
     * @param gameId unique ID of a game
     */
    public Game(int gameId) {
        this.gameId = gameId;
    }

    /**
     * return the unique ID of a game
     * @return the unique ID of a game
     */
    public int getGameId() {
        return this.gameId;
    }

}
