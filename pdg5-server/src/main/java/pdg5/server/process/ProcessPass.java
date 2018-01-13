package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Pass;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientHandler;

/**
 * Class built by the server to respond to a protocol.Pass sent by a client
 * It try to pass or throw 2 Tiles
 * for the associated client connected in a given (id) game
 *
 * @author Jimmy Verdasca
 */
public class ProcessPass implements GenericProcess {

    private final GameController gameController;
    private final Pass pass;
    private final ClientHandler clientHandler;

    public ProcessPass(Pass pass, GameController gameController, ClientHandler clientHandler) {
        this.pass = pass;
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    @Override
    public Message execute() {
        return gameController.pass(pass.getIdGame(), clientHandler.getPlayerId());
    }

}
