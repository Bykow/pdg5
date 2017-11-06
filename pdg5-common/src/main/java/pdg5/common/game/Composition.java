package pdg5.common.game;

import java.util.Arrays;


/**
 *
 */
public class Composition {

   private enum Square {
      NORMAL {
         @Override
         public int getFinalValue(Tile tile) {
            return tile.getValue();
         }
         
      }, DOUBLE {
         @Override
         public int getFinalValue(Tile tile) {
            return tile.getValue() * 2;
         }
         
      }, TRIPLE {
         @Override
         public int getFinalValue(Tile tile) {
            return tile.getValue() * 3;
         }
         
      }, BONUS {
         @Override
         public int getFinalValue(Tile tile) {
            return tile.getValue() + 10;
         }
         
      };
      public abstract int getFinalValue(Tile tile);
   }

   private static final int WORD_MAX_SIZE = 7;
   private final Tile[] word;
   private Square[] bonus;

   public Composition() {
      word = new Tile[WORD_MAX_SIZE];
      bonus = new Square[WORD_MAX_SIZE];
   }
   
   public void setBonus(Square[] bonusList) throws IllegalArgumentException {
      if(bonusList.length != WORD_MAX_SIZE) {
         throw new IllegalArgumentException(String.format("the length of array bonus parameter is not %d", WORD_MAX_SIZE));
      }
      bonus = Arrays.copyOf(bonusList, WORD_MAX_SIZE);
   }

   public boolean push(Tile tile) {
      for (int i = 0; i < WORD_MAX_SIZE; i++) {
         if (word[i] == null) {
            word[i] = tile;
            return true;
         }
      }
      return false;
   }

   public boolean add(Tile tile, int position) {
      if (word[position] == null) {
         word[position] = tile;
         return true;
      }
      return false;
   }

   public Tile remove(int position) {
      Tile temp = word[position];
      word[position] = null;
      return temp;
   }

   public int getValue(Game.Board board) throws IllegalStateException {
      if(!isValid()) {
         throw new IllegalStateException("This isn't a valid word");
      }
      int score = 0;
      for (int i = 0; i < WORD_MAX_SIZE; i++) {
         score += bonus[i].getFinalValue(word[i]);
      }
      if(board.getBonus().isEmpty()) {
         score *= 2;
      } else {
         for (Tile bonusTile : board.getBonus()) {
            score -= bonusTile.getValue();
         }
      }
      return score;
   }

   private boolean isValid() {
      boolean endOfWord = false;
      int sizeOfWord = 0;
      for (Tile tile : word) {
         if (tile == null) {
            endOfWord = true;
         } else if (endOfWord) {
            return false;
         } else {
            sizeOfWord++;
         }
      }
      if(sizeOfWord < 2) {
         return false;
      }
      return true;
   }
}
