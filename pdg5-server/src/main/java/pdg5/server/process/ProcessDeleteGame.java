/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.process;

import pdg5.common.protocol.DeleteGame;
import pdg5.common.protocol.Message;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientHandler;

/**
 *
 * @author Jimmy Verdasca
 */
public class ProcessDeleteGame implements GenericProcess {

   private DeleteGame deleteGame;
   private GameController gameController;
   private final ClientHandler clientHandler;

   public ProcessDeleteGame(DeleteGame deleteGame, GameController gameController, ClientHandler clientHandler) {
      this.deleteGame = deleteGame;
      this.gameController = gameController;
      this.clientHandler = clientHandler;
   }
   
   @Override
   public Message execute() {
      return gameController.deleteGame(deleteGame.getIdGame(), clientHandler.getPlayerId());
   }
   
}
