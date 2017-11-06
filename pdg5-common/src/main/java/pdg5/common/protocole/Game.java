package pdg5.common.protocole;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class Game implements Serializable {

    private int gameId;

    public Game(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return this.gameId;
    }
    
}
