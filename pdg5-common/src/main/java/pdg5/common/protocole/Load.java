package pdg5.common.protocole;

import java.util.List;

/**
 *
 * @author Maxime Guillod
 */
public class Load implements IClientRequest {

    private List<Game> games;

    public Load(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}