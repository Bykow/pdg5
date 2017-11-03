package pdg5.common.game;

import java.util.List;
import pdg5.common.Protocol;

/**
 * Class representing the model of the game.
 * It contains an inner class Board who is the letters 
 * (bonus and normal letters) manipulated by a player.
 */
public class Game {
   public class Board {
      private List<Tile> bonus;
      private List<Tile> letters;
      
      public List<Tile> getBonus() {
         return bonus;
      }
      
      public List<Tile> getLetters() {
         return letters;
      }
   }
   
   private Board[] board;
   
   public Game() {
      
   }
   
   public Game(Protocol.Game game) {
      
   }
}
