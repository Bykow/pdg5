package pdg5.common.game;

import java.io.Serializable;
import java.util.Arrays;


/**
 * Class representing the Tiles that the user is currently 
 * manipulating to make and to send to the server a word.
 */
public class Composition implements Serializable{

   /**
    * type of existing square where the player can put the letters
    * The type can modify the value of a Tile, or a word
    * The values can be *1, *2, *3 or +10
    */
   public enum Square {
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
      /**
       * return the new value when a Tile pass through a square
       * 
       * @param tile put in this square
       * @return the new value when a Tile pass through a square
       */
      public abstract int getFinalValue(Tile tile);
   }

   // Max size of a word
   private static final int WORD_MAX_SIZE = 7; 
   // seven or less Tiles representing the current word
   private final Tile[] word; 
   // seven current  bonus square where letters can obtain more values
   private Square[] bonus; 

   /**
    * Constructor
    */
   public Composition() {
      word = new Tile[WORD_MAX_SIZE];
      bonus = new Square[WORD_MAX_SIZE];
   }
   
   /**
    * set a new Square bonus list for a turn
    * 
    * @param bonusList the new Square bonus list for this turn
    * @throws IllegalArgumentException if the length of the given array 
    * isn't the good size (WORD_MAX_SIZE)
    */
   public void setBonus(Square[] bonusList) throws IllegalArgumentException {
      if(bonusList.length != WORD_MAX_SIZE) {
         throw new IllegalArgumentException(String.format("the length of array bonus parameter is not %d", WORD_MAX_SIZE));
      }
      bonus = Arrays.copyOf(bonusList, WORD_MAX_SIZE);
   }

   /**
    * add a Tile at the first free position
    * 
    * @param tile we want to add
    * @return True if the Tile as been put or False else
    */
   public boolean push(Tile tile) {
      for (int i = 0; i < WORD_MAX_SIZE; i++) {
         if (word[i] == null) {
            word[i] = tile;
            return true;
         }
      }
      return false;
   }

   /**
    * add a Tile at a given position, works only if the position is empty
    * Return True if the Tile as been put or False else
    * 
    * @param tile we want to add
    * @param position where we want to add the Tile
    * @return True if the Tile as been put or False else
    */
   public boolean add(Tile tile, int position) {
      if (word[position] == null) {
         word[position] = tile;
         return true;
      }
      return false;
   }

   /**
    * remove a Tile at the given position and return the removed Tile
    * 
    * @param position where we want to remove the Tile
    * @return the removed Tile
    */
   public Tile remove(int position) {
      Tile temp = word[position];
      word[position] = null;
      return temp;
   }

   /**
    * return the current value's word of the Composition
    * We need a Board to know if a letter comes from a bonus letter.
    * 
    * @param board where come from the letters
    * @return the current value's word of the Composition
    * @throws IllegalStateException if currently the Composition 
    * don't contain a potential word
    */
   public int getValue(Board board) throws IllegalStateException {
      // check if the composition is potentially a word
      if(!isValid()) {
         throw new IllegalStateException("This isn't a valid word");
      }
      
      int score = 0;
      // pass the letters through the square to know the real current value
      for (int i = 0; i < WORD_MAX_SIZE; i++) {
         if(word[i] != null) {
            score += bonus[i].getFinalValue(word[i]);
         }
      }
      
      // double the word's value if we used all the bonus letters
      if(board.getBonus().isEmpty()) {
         score *= 2;
      } else {
         for (Tile bonusTile : board.getBonus()) {
            score -= bonusTile.getValue();
         }
      }
      return score;
   }

   /**
    * return true if the Composition is potentially a word 
    * It means the word begins at first square, his of length two 
    * or more and has no hole.
    * 
    * @return true if the Composition is potentially a word, false else
    */
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
      
      return sizeOfWord >= 2;
   }
}
