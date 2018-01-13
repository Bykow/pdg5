package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.NewGame;
import pdg5.common.protocol.Noop;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * When a client ask for a new game, the server use this class to add the id client,
 * that asked for a new game, to the queue of matchmaking.
 */
public class ProcessNewGame implements GenericProcess {

   /**
    * GameController that will create and stock the new game
    */
    private final GameController gameController;
    
    /**
     * contains id of players who will play the new game
     */
    private final NewGame newGame;
    
    /**
     * manager of the user who are connected
     */
    private final ServerActiveUser activeUser;
    
    /**
     * manager of the socket where we received the NewGame
     */
    private final ClientHandler clientHandler;

    /**
     * Constructor
     *
     * @param newGame contains id of players who will play the new game
     * @param gm GameController that will create and stock the new game
     * @param activeUser manager of the user who are connected
     * @param clientHandler manager of the socket where we received the NewGame
     */
    public ProcessNewGame(NewGame newGame, GameController gm, ServerActiveUser activeUser, ClientHandler clientHandler) {
        this.newGame = newGame;
        this.gameController = gm;
        this.activeUser = activeUser;
        this.clientHandler = clientHandler;
    }

    /**
     * add the idClient to the matchmaking queue.
     *
     * @return to the client an ErrorMessage to signal we are treating his request
     */
    @Override
    public Message execute() {
        switch (newGame.getType()) {
            case RANDOM:
                return gameController.newGame(clientHandler.getPlayerId());
            case REQUEST:
                activeUser.getClientHandler(newGame.getIdOpponentWished()).addToQueue(newGame);
                break;
            case ACCEPT:
                return gameController.addNewGame(clientHandler.getPlayerId(), newGame.getIdOpponentWished());
            case REFUSE:
                break;
            default:
                System.err.println("Le type de NewGame est invalide");
                break;
        }
        return new Noop(Noop.Sender.SERVER);
    }

}
