package pdg5.common.protocol;

import java.util.Date;

/**
 * Class sent through the connection by a client or the server representing, a chat
 * message
 */
public class Chat extends Message {

    /**
     * enum representing the type of the person who created this Chat originally
     */
    public enum SENDER {
        USER, OPPONENT, SERVER
    }

    /**
     * the type of the person who created this Chat originally
     */
    private SENDER sender;

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
        this(message, gameId, new Date().getTime(), SENDER.USER);
    }

    /**
     * Constructor for client creating new messages
     *
     * @param message the message or historic we want to send
     * @param gameId the unique ID of the message
     * @param sender the type of the person who created this Chat originally
     */
    public Chat(String message, int gameId, SENDER sender) {
        this(message, gameId, new Date().getTime(), sender);
    }

    /**
     * Constructor for server sending stocked messages
     *
     * @param message the message or historic we want to send
     * @param gameId the unique ID of the message
     * @param timeStamp date of the message creation
     * @param sender the type of the person who created this Chat originally
     */
    public Chat(String message, int gameId, long timeStamp, SENDER sender) {
        this.message = message;
        this.gameId = gameId;
        this.timeStamp = timeStamp;
        this.sender = sender;
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

    /**
     * return the identity of the sender relativ to the game
     *
     * @return the identity of the sender relativ to the game
     */
    public SENDER getSender() {
        return sender;
    }

    /**
     * set the Sender usefull for server when he wants to transfert easily a Chat
     *
     * @param sender the new identity of the sender relativ to the game
     */
    public void setSender(SENDER sender) {
        this.sender = sender;
    }

}
