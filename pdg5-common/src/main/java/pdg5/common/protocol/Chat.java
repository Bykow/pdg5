package pdg5.common.protocol;

import java.util.Date;

/**
 * Class sended through the connection by a client or the server representing,
 * a chat message
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
     * Constructor for client creating new messages
     * 
     * @param message the message or historic we want to send
     * @param gameId the unique ID of the message
     */
    public Chat(String message, int gameId) {
        this(message, gameId, new Date().getTime());
    }
    
    /**
     * Constructor for server sending stocked messages
     * 
     * @param message the message or historic we want to send
     * @param gameId the unique ID of the message
     * @param timeStamp date of the message creation
     */
    public Chat(String message, int gameId, long timeStamp) {
        this.message = message;
        this.gameId = gameId;
        this.timeStamp = timeStamp;
    }

    /**
     * return a unique ID for the message
     * 
     * @return a unique ID for the message
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * return a String representing the message
     * 
     * @return a String representing the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * return the date when the message as been sent
     * 
     * @return the date when the message as been sent 
     */
    public long getTimeStamp() {
       return timeStamp;
    }

    
}
