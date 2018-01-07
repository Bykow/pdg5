package pdg5.common.protocol;

import java.util.Date;

/**
 * Class sended through the connection by a client or the server representing,
 * if the message comes from a client, a message.
 * If the message comes from the server, an historic.
 */
public class Chat extends Message {

   /**
    * time when this instance was created
    */
    private long timeStamp;
    
    /**
     * String representing the message
     */
    private String message;
    
    /**
     * unique id of the game
     */
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
