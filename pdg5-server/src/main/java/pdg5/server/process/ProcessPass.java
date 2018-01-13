package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Pass;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientHandler;

/**
 * Class built by the server to respond to a protocol.Pass sent by a client It try to
 * pass or throw 2 Tiles for the associated client connected in a given (id) game
 */
public class ProcessPass implements GenericProcess {

   /**
    * the orignal Pass request received by the server
    */
    private final Pass pass;
    
    /**
     * GameController that will choose to pass or throw
     */
    private final GameController gameController;
    
    /**
     * manager of the socket where we received the Pass
     */
    private final ClientHandler clientHandler;

    /**
     * Constructor
     * 
     * @param pass the orignal Pass request received by the server
     * @param gameController GameController that will choose to pass or throw
     * @param clientHandler manager of the socket where we received the Pass
     */
    public ProcessPass(Pass pass, GameController gameController, ClientHandler clientHandler) {
        this.pass = pass;
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    /**
     * try to pass or throw depending on the state of the game
     * 
     * @return a Game with the new state of the game
     */
    @Override
    public Message execute() {
        return gameController.pass(pass.getIdGame(), clientHandler.getPlayerId());
    }

}
