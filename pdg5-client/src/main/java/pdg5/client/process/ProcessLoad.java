package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;

public class ProcessLoad implements GenericProcess {

    private Load load;
    private MainController mainController;

    public ProcessLoad(Load load, MainController mainController) {
        this.load = load;
        this.mainController = mainController;
    }

    @Override
    public Message execute() {
        if (!load.getGames().isEmpty()) {
            mainController.getLobyController().addLoad(load);
        }
        return null;
    }
}
