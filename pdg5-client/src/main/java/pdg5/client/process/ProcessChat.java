package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.Chat;
import pdg5.common.protocol.Message;

/**
 * Created on 10.01.18 by Bykow
 */
public class ProcessChat implements GenericProcess {

    private Chat chat;
    private MainController mainController;

    public ProcessChat(Chat chat, MainController mainController) {
        this.chat = chat;
        this.mainController = mainController;
    }

    @Override
    public Message execute() {
        mainController.getLobyController().updateChat(chat);
        return null;
    }
}
