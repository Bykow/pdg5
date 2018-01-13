package pdg5.common.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class sent through the connection by the server representing the list of available
 * games a player have
 */
public class Load extends Message {

    /**
     * List of available games a player have (even finished ones)
     */
    private final List<Game> games;

    /**
     * the chat historic of this game
     */
    private final Map<Integer, List<Chat>> historic;

    /**
     * Constructor
     *
     * @param games list of available games
     * @param historic the chat historic
     */
    public Load(List<Game> games, Map<Integer, List<Chat>> historic) {
        this.games = games;
        this.historic = historic;
    }

    /**
     * Constructor empty
     */
    public Load() {
        this.games = new ArrayList<>();
        historic = new HashMap<>();
    }

    /**
     * return the list of available games of a player
     *
     * @return the list of available games of a player
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * return the list of chat message of one game
     *
     * @param idGame unique id of the game we want the historic
     * @return the list of chat message of one game
     */
    public List<Chat> getHistoricOfGame(int idGame) {
        return historic.get(idGame);
    }

    /**
     * return the map containing all the historic of games
     *
     * @return the map containing all the historic of games
     */
    public Map<Integer, List<Chat>> getHistoric() {
        return historic;
    }
}
