package pdg5.common.protocole;

import java.util.List;

/**
 * Class sended through the connection representing
 * the list of available games a player have.
 */
public class Load implements IClientRequest {

   //List of available games a player have (even finished ones)
    private List<Game> games;

    /**
     * Constructor
     * @param games list of available games
     */
    public Load(List<Game> games) {
        this.games = games;
    }

    /**
     * @return the list of available games of a player
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * Method immediately used when the server or client receive the object.
     * Actualize the UI in the client with all the games in all categories.
     */
    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
