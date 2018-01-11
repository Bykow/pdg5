package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;

public class ProcessErrorMessage implements GenericProcess {

    private ErrorMessage message;
    private MainController mainController;


    public ProcessErrorMessage(ErrorMessage message, MainController mainController) {
        this.message = message;
        this.mainController = mainController;
    }

    @Override
    public Message execute() {
        mainController.displayToast(message.getError());
        System.out.println("ErrorMessage : " + message.getError());
        return null;
    }
}
