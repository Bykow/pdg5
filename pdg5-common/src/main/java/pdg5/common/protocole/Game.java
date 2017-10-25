package pdg5.common.protocole;

/**
 * 
 * Class sended through the connection  by the server representing
 * a full game with two players.
 */
public class Game implements IClientRequest {

    private int gameId;

    /**
     * Constructor 
     * @param gameId unique ID of a game
     */
    public Game(int gameId) {
        this.gameId = gameId;
    }

    /**
     * return the unique ID of a game
     * @return the unique ID of a game
     */
    public int getGameId() {
        return this.gameId;
    }

    /**
     * method immediately used when the client receive the object.
     * When the client receive these Game class he can read the current state 
     * of these game and actualize his UI.
     */
    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
