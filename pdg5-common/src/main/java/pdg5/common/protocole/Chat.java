package pdg5.common.protocole;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class Chat implements Serializable {

    private String message;
    private int gameId;

    public Chat(String message, int gameId) {
        this.message = message;
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getMessage() {
        return message;
    }

}
