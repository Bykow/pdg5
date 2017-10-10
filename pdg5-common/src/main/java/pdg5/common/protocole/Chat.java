package pdg5.common.protocole;

/**
 *
 * @author Maxime Guillod
 */
public class Chat implements IServerRequest, IClientRequest {

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

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
