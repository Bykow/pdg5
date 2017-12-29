
package pdg5.client.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.NewGame;
import pdg5.common.protocol.Noop;

/**
 *
 */
public class ProcessNewGame implements GenericProcess{

   NewGame newGame;

   public ProcessNewGame(NewGame newGame) {
      this.newGame = newGame;
   }
   
   @Override
   public Message execute() {
      switch(newGame.getType()){
         case REQUEST:
            System.out.println("receiving request for game");
            newGame.setType(NewGame.TYPE.ACCEPT);
            return newGame;
      }
      
      return new Noop(Noop.Sender.CLIENT);
   }

}
