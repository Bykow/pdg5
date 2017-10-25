package pdg5.common.protocole;

import java.util.Date;

/**
 * Class sended through the connection by a client or the server representing,
 * if the message comes from a client, a message.
 * If the message comes from the server, an historic.
 */
public class Chat implements IServerRequest, IClientRequest {

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

    /**
     * Method immediately used when the server receive the object.
     * It will deliver the message to the clients concerned.
     */
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method immediately used when the client receive the object.
     * It will actualize the messages in the client 
     */
    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
