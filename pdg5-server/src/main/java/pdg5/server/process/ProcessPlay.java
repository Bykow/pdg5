package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Play;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * Class built by the server to respond to a protocol.Play sent by a client It try to
 * play a given word by the client in a game
 */
public class ProcessPlay implements GenericProcess {

   /**
    * original Play request received by the server
    */
    private final Play play;
    
    /**
     * GameController that will try to play the move
     */
    private final GameController gameController;
    
    /**
     * manager of the manager of the socket where we received the Play
     */
    private final ClientHandler clientHandler;

    /**
     * Constructor
     *
     * @param play original Play request received by the server
     * @param gameController GameController that will try to play the move
     * @param clientHandler manager of the socket where we received the Play
     */
    public ProcessPlay(Play play, GameController gameController, ClientHandler clientHandler) {
        this.play = play;
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    /**
     * Try to play a word contained in the composition If it success, the GameModel
     * is updated with new state and the new state is sent to both players If it
     * fail, for exemple because the word isn't valide, an ErrorMessage with the fail
     * cause is sent back to the currentPlayer
     *
     * @return if it success, a Game with updated state, else an ErrorMessage
     */
    @Override
    public Message execute() {
        return gameController.play(play.getGameID(), clientHandler.getPlayerId(), play.getComposition());
    }
}
