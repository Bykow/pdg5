package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.End;
import pdg5.common.protocol.Message;

/**
 * Created on 12.01.18 by Bykow
 */
public class ProcessEnd implements GenericProcess {

    private End end;
    private MainController mainController;

    public ProcessEnd(End end, MainController mainController) {
        this.end = end;
        this.mainController = mainController;
    }

    @Override
    public Message execute() {
        mainController.getLobyController().gameIsFinished(end);
        return null;
    }
}
