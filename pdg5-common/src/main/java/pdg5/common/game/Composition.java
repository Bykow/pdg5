package pdg5.common.game;

import pdg5.common.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Composition {

   private enum square {
      NORMAL, DOUBLE, TRIPLE, BONUS
   }

   private static final int WORD_MAX_SIZE = 7;
   private Tile[] word;

   public Composition() {
      word = new Tile[WORD_MAX_SIZE];
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

   public int getValue(Game.Board board) {
      return 0;
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
