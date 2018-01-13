package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;

/**
 * ProcessLoad, behaviour for client when receiving a Chat message
 */
public class ProcessLoad implements GenericProcess {

    private Load load;
    private MainController mainController;

    /**
     * Ctor
     *
     * @param load           message load
     * @param mainController link to Main controller
     */
    public ProcessLoad(Load load, MainController mainController) {
        this.load = load;
        this.mainController = mainController;
    }

    /**
     * Execute the behaviour when receiving a Load message
     *
     * @return null, we could send back something to server
     */
    @Override
    public Message execute() {
        if (!load.getGames().isEmpty()) {
            mainController.getLobyController().addLoad(load);
        }
        return null;
    }
}
