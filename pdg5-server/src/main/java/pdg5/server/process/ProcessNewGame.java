package pdg5.server.process;

import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;

/**
 * When a client ask for a new game, the server use this class to add 
 * the id client, that asked for a new game, to the queue of matchmaking.
 */
public class ProcessNewGame implements GenericProcess {

   public ProcessNewGame(int idPlayerAskingForGame) {
      
   }
   
   /**
    * add the idClient to the matchmaking queue.
    * @return to the client a message to signal we are treating his request
    */
   @Override
   public Message execute() {
      //TODO
      return new ErrorMessage("Waiting for opponent being selected.");
   }
   
}
