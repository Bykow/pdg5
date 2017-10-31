package pdg5.common.protocol;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maxime Guillod
 */
public class Load implements Serializable {

    private List<Game> games;

    public Load(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

}
