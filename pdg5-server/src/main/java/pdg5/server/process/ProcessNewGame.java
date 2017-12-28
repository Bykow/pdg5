package pdg5.server.process;

import pdg5.common.game.Utils;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.NewGame;
import pdg5.common.protocol.NewGame.TYPE;
import pdg5.common.protocol.Noop;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.util.ServerActiveUser;

/**
 * When a client ask for a new game, the server use this class to add the id
 * client, that asked for a new game, to the queue of matchmaking.
 */
public class ProcessNewGame implements GenericProcess {

   private final GameController gameController;
   private final NewGame newGame;
   private final ServerActiveUser activeUser;

   /**
    * Constructor
    *
    * @param newGame contains id of players who will play the new game
    * @param gm GameController that will create and stock the new game
     * @param activeUser
    */
   public ProcessNewGame(NewGame newGame, GameController gm, ServerActiveUser activeUser) {
      this.newGame = newGame;
      this.gameController = gm;
      this.activeUser = activeUser;
   }

   /**
    * add the idClient to the matchmaking queue.
    *
    * @return to the client a message to signal we are treating his request
    */
   @Override
   public Message execute() {
      switch (newGame.getType()) {
         case RANDOM:
            return gameController.newGame(newGame.getIdPlayerAsking());
         case REQUEST:
            activeUser.getClientHandler(newGame.getIdOpponentWished()).addToQueue(newGame);
            break;
         case ACCEPT:
            return gameController.addNewGame(newGame.getIdPlayerAsking(), newGame.getIdOpponentWished());
         case REFUSE:
            break;
         default:
            System.err.println("Le type de NewGame est invalide");
            break;
      }
      return new Noop(Noop.Sender.SERVER);
   }

}
