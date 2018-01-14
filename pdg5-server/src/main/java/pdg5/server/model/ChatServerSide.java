package pdg5.server.model;

import pdg5.common.protocol.Chat;

/**
 * represent a message data in the point of view of the server
 * it principally add the idPlayer from a Protocol.Chat
 */
public class ChatServerSide {

    /**
     * date of the creation of the Chat message
     */
    private long timeStamp;

    /**
     * unique id of the player who created this message
     */
    private int idSender;

    /**
     * the type of the sender (USER, OPPONENT or SERVER)
     */
    private Chat.SENDER senderType;

    /**
     * content of the message
     */
    private String message;

    /**
     * unique id of the game where was created the message
     */
    private int idGame;

    /**
     * Constructor
     *
     * @param timeStamp  date of the creation of the Chat message
     * @param idSender   unique id of the player who created this message
     * @param senderType the type of the sender (USER, OPPONENT or SERVER)
     * @param message    content of the message
     * @param idGame     unique id of the game where was created the message
     */
    public ChatServerSide(long timeStamp, int idSender, Chat.SENDER senderType, String message, int idGame) {
        this.timeStamp = timeStamp;
        this.idSender = idSender;
        this.senderType = senderType;
        this.message = message;
        this.idGame = idGame;
    }

    /**
     * return the date of the creation of the Chat message
     *
     * @return the date of the creation of the Chat message
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * return the unique id of the player who created this message
     *
     * @return the unique id of the player who created this message
     */
    public int getIdSender() {
        return idSender;
    }

    /**
     * return the type of the sender (USER, OPPONENT or SERVER)
     *
     * @return the type of the sender (USER, OPPONENT or SERVER)
     */
    public Chat.SENDER getSenderType() {
        return senderType;
    }

    /**
     * return the content of the message
     *
     * @return the content of the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * return the unique id of the game where was created the message
     *
     * @return the unique id of the game where was created the message
     */
    public int getIdGame() {
        return idGame;
    }
}
