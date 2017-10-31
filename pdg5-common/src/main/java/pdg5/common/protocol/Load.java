package pdg5.common.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxime Guillod
 */
public class Load extends Message {

    private List<Game> games;

    public Load(List<Game> games) {
        this.games = games;
    }

    public Load() {
        this.games = new ArrayList<Game>();
    }

    public List<Game> getGames() {
        return games;
    }

}
