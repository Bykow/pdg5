package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;

public class ProcessGame implements GenericProcess {

    private Game game;
    private MainController mainController;

    public ProcessGame(Game game, MainController mainController) {
        this.game = game;
        this.mainController = mainController;
    }

    @Override
    public Message execute() {
        mainController.getGameController().updateGame(game);
        return null;
    }
}
