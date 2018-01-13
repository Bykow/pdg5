package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Message;

/**
 * ProcessGame, behaviour for client when receiving a Game message
 */
public class ProcessGame implements GenericProcess {

    private Game game;
    private MainController mainController;

    /**
     * Ctor
     *
     * @param game           message game
     * @param mainController link to Main controller
     */
    public ProcessGame(Game game, MainController mainController) {
        this.game = game;
        this.mainController = mainController;
    }

    /**
     * Execute the behaviour when receiving a Game message
     *
     * @return null, we could send back something to server
     */
    @Override
    public Message execute() {
        mainController.getLobyController().updateGame(game);
        return null;
    }
}
