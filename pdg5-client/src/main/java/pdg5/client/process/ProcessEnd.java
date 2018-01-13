package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.End;
import pdg5.common.protocol.Message;

/**
 * ProcessEnd, behaviour for client when receiving an End message
 */
public class ProcessEnd implements GenericProcess {

    private End end;
    private MainController mainController;

    /**
     * Ctor
     *
     * @param end            message End
     * @param mainController link to mainController
     */
    public ProcessEnd(End end, MainController mainController) {
        this.end = end;
        this.mainController = mainController;
    }

    /**
     * Execute the behaviour when receiving an End message
     *
     * @return null, we could send back something to server
     */
    @Override
    public Message execute() {
        mainController.getLobyController().gameIsFinished(end);
        return null;
    }
}
