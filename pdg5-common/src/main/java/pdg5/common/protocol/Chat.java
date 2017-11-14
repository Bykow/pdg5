package pdg5.common.protocol;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Maxime Guillod
 */
public class Chat implements Serializable {

    private long timeStamp;
    private String message;
    private int gameId;

    /**
     * Constructor
     * @param message the message or historic we want to send
     * @param gameId the unique ID of the message
     */
    public Chat(String message, int gameId) {
        this.message = message;
        this.gameId = gameId;
        this.timeStamp = new Date().getTime();
    }

    /**
     * return a unique ID for the message.
     * @return a unique ID for the message.
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * return a String representing the message.
     * @return a String representing the message.
     */
    public String getMessage() {
        return message;
    }

}
