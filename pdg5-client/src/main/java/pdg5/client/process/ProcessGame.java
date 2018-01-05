package pdg5.client.process;

import pdg5.common.protocol.Game;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;

/**
 *
 */
public class ProcessGame implements GenericProcess {

   Game game;

   public ProcessGame(Game game) {
      this.game = game;
   }

   @Override
   public Message execute() {
      System.out.println("received new game");
      System.out.println(String.format("Me : %s\n, Opponent:%s\n\n", game.getNamePlayer(), game.getOpponentName()));

      System.out.print("Bonus letters : ");
      game.getBonusLetters().forEach((bonusLetter) -> {
         System.out.print(bonusLetter.getLetter());
      });
      
      System.out.print("\n\nLetters : ");
      game.getAddedTile().forEach((letters) -> {
         System.out.print(letters.getLetter());
      });
      
      return new Noop(Noop.Sender.CLIENT);
   }

}
