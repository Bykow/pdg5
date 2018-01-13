package pdg5.server.model;

import pdg5.common.protocol.Chat;

/**
 * @author Jimmy Verdasca
 */
public class ChatServerSide {

    private long timeStamp;
    private int idSender;
    private String message;
    private int idGame;
    private Chat.SENDER senderType;

    /**
     * Constructor
     *
     * @param timeStamp
     * @param idSender
     * @param senderType
     * @param message
     * @param idGame
     */
    public ChatServerSide(long timeStamp, int idSender, Chat.SENDER senderType, String message, int idGame) {
        this.timeStamp = timeStamp;
        this.idSender = idSender;
        this.senderType = senderType;
        this.message = message;
        this.idGame = idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getIdSender() {
        return idSender;
    }

    public Chat.SENDER getSenderType() {
        return senderType;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
