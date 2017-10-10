package pdg5.common.protocole;

/**
 *
 * @author Maxime Guillod
 */
public class Game implements IClientRequest {

    private int gameId;

    public Game(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return this.gameId;
    }

    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
