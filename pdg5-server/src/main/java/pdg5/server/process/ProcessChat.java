/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.process;

import pdg5.common.protocol.Chat;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;
import pdg5.server.model.ChatServerSide;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientHandler;

/**
 *
 * @author Jimmy Verdasca
 */
public class ProcessChat implements GenericProcess {

    private final Chat chat;
    private final GameController gameController;
    private final ClientHandler clientHandler;

    public ProcessChat(Chat chat, GameController gameController, ClientHandler clientHandler) {
        this.chat = chat;
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    @Override
    public Message execute() {
        if (chat.getSender() != Chat.SENDER.USER) {
            return new ErrorMessage("you are not allowed to send message for others");
        }

        ChatServerSide serverChat = new ChatServerSide(chat.getTimeStamp(), clientHandler.getPlayerId(), Chat.SENDER.USER, chat.getMessage(), chat.getGameId());
        gameController.addChat(serverChat, false);
        return new Noop(Noop.Sender.SERVER);
    }

}
