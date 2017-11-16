/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Play;
import pdg5.server.model.GameController;

/**
 *
 * @author Jimmy Verdasca
 */
public class ProcessPlay implements GenericProcess {
   
   private final Play play;
   private final GameController gameController;
   
   public ProcessPlay(Play play, GameController gameController) {
      this.play = play;
      this.gameController = gameController;
   }

   @Override
   public Message execute() {
      return gameController.play(play.getGameID(), play.getPlayerID(), play.getComposition());
   }
}
