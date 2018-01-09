/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.process;

import pdg5.common.protocol.Chat;
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
   private Chat chat;
   private GameController gameController;
   private ClientHandler clientHandler;

   public ProcessChat(Chat chat, GameController gameController, ClientHandler clientHandler) {
      this.chat = chat;
      this.gameController = gameController;
      this.clientHandler = clientHandler;
   }

   @Override
   public Message execute() {
      gameController.addChat(new ChatServerSide(chat.getTimeStamp(), clientHandler.getPlayerId(), chat.getMessage(), chat.getGameId()));
      return new Noop(Noop.Sender.SERVER);
   }
   
   
}
