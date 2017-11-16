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
 * Class built by the server to respond to a protocol.Play sent by a client
 * It try to play a given word by the client in a game
 */
public class ProcessPlay implements GenericProcess {
   
   private final Play play;
   private final GameController gameController;
   
   /**
    * Constructor
    * 
    * @param play the protocol item containing the composition that a client wants to play
    * @param gameController GameController that will try to play the move
    */
   public ProcessPlay(Play play, GameController gameController) {
      this.play = play;
      this.gameController = gameController;
   }

   /**
    * Try to play a word contained in the composition
    * If it success, the GameModel is updated with new state
    * and the new state is sent to both players
    * If it fail, for exemple because the word isn't valide,
    * an ErrorMessage with the fail cause is sent back to the currentPlayer
    * 
    * @return if it success, a Game with updated state, else an ErrorMessage
    */
   @Override
   public Message execute() {
      return gameController.play(play.getGameID(), play.getPlayerID(), play.getComposition());
   }
}
