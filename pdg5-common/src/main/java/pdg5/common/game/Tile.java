package pdg5.common.game;

/**
 * A Tile represents a letter and its value on in the game. The value is
 * directly linked to the langage of the game.
 * An id is necessary to recognize two same letters  with same value, 
 * when one is a bonus tile and an other is a normal letter.
 */
public class Tile {
    private int id;
    private char letter;
    private int value;

    /**
     * Constructor
     *
     * @param letter
     * @param value
     */
    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    /**
     * Returns the letter of a Tile
     *
     * @return char letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Returns the value of a letter on a Tile
     *
     * @return int value
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a String representing the Tile.java class.
     * The format is "Letter, Value"
     * 
     * @return  a String representing the Tile.java class
     */
    public String toString() {
        return getLetter() + ", " + getValue();
    }
}
