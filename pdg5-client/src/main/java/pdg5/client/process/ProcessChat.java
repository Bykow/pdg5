package pdg5.client.process;

import pdg5.client.controller.MainController;
import pdg5.common.protocol.Chat;
import pdg5.common.protocol.Message;

/**
 * ProcessChat, behaviour for client when receiving a Chat message
 */
public class ProcessChat implements GenericProcess {

    private Chat chat;
    private MainController mainController;

    /**
     * Ctor
     *
     * @param chat message Chat
     * @param mainController link to Main controller
     */
    public ProcessChat(Chat chat, MainController mainController) {
        this.chat = chat;
        this.mainController = mainController;
    }

    /**
     * Execute the behaviour when receiving a Chat message
     *
     * @return null, we could send back something to server
     */
    @Override
    public Message execute() {
        mainController.getLobyController().updateChat(chat);
        return null;
    }
}
