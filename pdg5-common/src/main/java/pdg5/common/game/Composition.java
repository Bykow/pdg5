package pdg5.common.game;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


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
         
      }, W {
         @Override
         public int getFinalValue(Tile tile) {
            return tile.getValue();
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

   // seven or less Tiles representing the current word
   private final Tile[] word; 
   // seven current  bonus square where letters can obtain more values
   private Square[] bonus; 

   /**
    * Constructor
    */
   public Composition() {
      word = new Tile[Utils.WORD_MAX_SIZE];
      bonus = new Square[Utils.WORD_MAX_SIZE];
   }
   
   /**
    * set a new Square bonus list for a turn
    * 
    * @param bonusList the new Square bonus list for this turn
    * @throws IllegalArgumentException if the length of the given array 
    * isn't the good size (WORD_MAX_SIZE)
    */
   public void setBonus(Square[] bonusList) throws IllegalArgumentException {
      if(bonusList.length != Utils.WORD_MAX_SIZE) {
         throw new IllegalArgumentException(String.format("the length of array bonus parameter is not %d", Utils.WORD_MAX_SIZE));
      }
      bonus = Arrays.copyOf(bonusList, Utils.WORD_MAX_SIZE);
   }

   /**
    * add a Tile at the first free position
    * 
    * @param tile we want to add
    * @return True if the Tile as been put or False else
    */
   public boolean push(Tile tile) {
      for (int i = 0; i < Utils.WORD_MAX_SIZE; i++) {
         if (word[i] == null) {
            word[i] = tile;
            return true;
         }
      }
      return false;
   }

   /**
    * return a String representation of the Composition 
    * with space instead of null Tile
    * 
    * @return a String representation of the Composition 
    * with space instead of null Tile
    */
   public String getStringForm() {
      StringBuilder sb = new StringBuilder();
      for (Tile tile : word) {
         sb.append(tile.getLetter());
      }
      return sb.toString();
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
    * remove all the Tiles in the Composition
    */
   public void removeAll() {
      for (int i = 0; i < word.length; i++) {
         remove(i);
      }
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
      for (int i = 0; i < Utils.WORD_MAX_SIZE; i++) {
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
    * TODO not implemented yet
    * @param letterValues
    * @return 
    */
   public int getValue(List<Integer> letterValues) {
      int score = 0;
      return score;
   }
   
   /**
    * return true if the Composition is potentially a word 
    * It means the word begins at first square, is of length two 
    * or more and has no hole.
    * 
    * @return true if the Composition is potentially a word, false otherwise
    */
   public boolean isValid() {
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
      
      return sizeOfWord >= Utils.WORD_MIN_SIZE;
   }
   
   /**
    * compare a Composition with an Object and 
    * return true if they have same fields and Class
    * 
    * @param o Compared Object
    * @return true if the two Object is of same class and have the same fields
    */
   public boolean equals(Object o) {
      return getClass().isInstance(o) &&
              getClass() == o.getClass() &&
              Arrays.equals(word, ((Composition) o).word) &&
              Arrays.equals(bonus, ((Composition) o).bonus);
   }

   /**
    * Hashcode mandatory for use of Equals
    * 
    * @return the hash as int
    */
   @Override
   public int hashCode() {
      int hash = 7;
      hash = 79 * hash + Arrays.deepHashCode(this.word);
      hash = 79 * hash + Arrays.deepHashCode(this.bonus);
      return hash;
   }
}
