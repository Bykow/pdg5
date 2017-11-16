package pdg5.server.process;

import pdg5.common.game.Utils;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.NewGame;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;

/**
 * When a client ask for a new game, the server use this class to add 
 * the id client, that asked for a new game, to the queue of matchmaking.
 */
public class ProcessNewGame implements GenericProcess {

   
   private GameController gameController;
   private ManageUser manageUser;
   private NewGame newGame;
   
   
   /**
    * Constructor
    * 
    * @param newGame contains id of players who will play the new game
    * @param gm GameController that will create and stock the new game
    */
   public ProcessNewGame(NewGame newGame, GameController gm) {
      this.newGame = newGame;
      this.gameController = gm;
   }
   
   /**
    * add the idClient to the matchmaking queue.
    * @return to the client a message to signal we are treating his request
    */
   @Override
   public Message execute() {
      if(newGame.getIdOpponentWished() == Utils.RANDOM_OPPONENT) {
         return gameController.askNewGame(newGame.getIdPlayerAsking());
      } else {
         return gameController.askOpponentNewGame(newGame.getIdPlayerAsking(), newGame.getIdOpponentWished());
      }
   }
   
}
