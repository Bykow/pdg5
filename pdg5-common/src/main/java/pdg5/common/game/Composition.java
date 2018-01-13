package pdg5.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the Tiles that the user is currently
 * manipulating to make and to send to the server a word.
 */
public class Composition implements Serializable {

    // seven or less Tiles representing the current word
    private final Tile[] word;
    // seven current  bonus square where letters can obtain more values
    private Square[] square;

    /**
     * Constructor
     */
    public Composition() {
        word = new Tile[Utils.WORD_MAX_SIZE];
        square = new Square[Utils.WORD_MAX_SIZE];
    }

    public Composition(Composition composition) {
        word = composition.word.clone();
        square = composition.square.clone();
    }

    public Square[] getSquare() {
        return square;
    }

    /**
     * set a new Square bonus list for a turn
     *
     * @param bonusList the new Square bonus list for this turn
     * @throws IllegalArgumentException if the length of the given array
     *                                  isn't the good size (WORD_MAX_SIZE)
     */
    public void setSquare(Square[] bonusList) throws IllegalArgumentException {
        if (bonusList.length != Utils.WORD_MAX_SIZE) {
            throw new IllegalArgumentException(String.format("the length of array bonus parameter is not %d", Utils.WORD_MAX_SIZE));
        }
        square = Arrays.copyOf(bonusList, Utils.WORD_MAX_SIZE);
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
            if (tile == null) {
                sb.append(" ");
            } else {
                sb.append(tile.getLetter());
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * add a Tile at a given position, works only if the position is empty
     * Return True if the Tile as been put or False else
     *
     * @param tile     we want to add
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
     *
     * @return the current value's word of the Composition
     * @throws IllegalStateException if currently the Composition
     *                               don't contain a potential word
     */
    public int getValue() throws IllegalStateException {
        // check if the composition is potentially a word
        if (!isValid()) {
            throw new IllegalStateException("This isn't a valid word");
        }

        int score = 0;
        // pass the letters through the square to know the real current value
        for (int i = 0; i < Utils.WORD_MAX_SIZE; i++) {
            if (word[i] != null) {
                score += square[i].getFinalValue(word[i]);
            }
        }

        return score;
    }

    /**
     * TODO not implemented yet
     *
     * @param letterValues
     * @return
     */
    public int getValue(List<Integer> letterValues) {
        int score = 0;
        return score;
    }

    public List<Tile> getWord() {
        List<Tile> returnList = new ArrayList<>(Arrays.asList(word));
        returnList.removeAll(Collections.singleton(null));
        return returnList;
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
                Arrays.equals(square, ((Composition) o).square);
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
        hash = 79 * hash + Arrays.deepHashCode(this.square);
        return hash;
    }

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

            @Override
            public String getText() {
                return null;
            }

        }, DOUBLE {
            @Override
            public int getFinalValue(Tile tile) {
                return tile.getValue() * 2;
            }

            @Override
            public String getText() {
                return "2xL";
            }

        }, TRIPLE {
            @Override
            public int getFinalValue(Tile tile) {
                return tile.getValue() * 3;
            }

            @Override
            public String getText() {
                return "3xL";
            }

        }, BONUS {
            @Override
            public int getFinalValue(Tile tile) {
                return tile.getValue() + 10;
            }

            @Override
            public String getText() {
                return "+10";
            }

        }, W {
            @Override
            public int getFinalValue(Tile tile) {
                return tile.getValue();
            }

            @Override
            public String getText() {
                return "W";
            }
        };

        /**
         * return the new value when a Tile pass through a square
         *
         * @param tile put in this square
         * @return the new value when a Tile pass through a square
         */
        public abstract int getFinalValue(Tile tile);

        public abstract String getText();
    }
}
