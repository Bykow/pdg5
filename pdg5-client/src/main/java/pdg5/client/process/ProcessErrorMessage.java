package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;

/**
 * ProcessErrorMessage, behaviour for client when recieving an error message
 */
public class ProcessErrorMessage implements GenericProcess {

    private ErrorMessage message;
    private MainController mainController;

    /**
     * Ctor
     *
     * @param message        error
     * @param mainController link to Main controller
     */
    public ProcessErrorMessage(ErrorMessage message, MainController mainController) {
        this.message = message;
        this.mainController = mainController;
    }

    /**
     * Execute the behaviour when receiving an Error message
     *
     * @return null, we could send back something to server
     */
    @Override
    public Message execute() {
        if (! message.getError().equals("Unhandled ErrorMessage is ServerRequestManager, default reached are you connected?")) {
            mainController.displayToast(message.getError());
        }
        return null;
    }
}
