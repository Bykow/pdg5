package pdg5.common.game;

import java.io.Serializable;
import java.util.List;

/**
 * A Tile represents a letter and its value on in the game. The value is
 * directly linked to the langage of the game.
 * An id is necessary to recognize two same letters  with same value,
 * when one is a bonus tile and an other is a normal letter.
 */
public class Tile implements Serializable {

    private final char letter;
    private final int value;
    private boolean bonus = false;

    /**
     * Constructor
     *
     * @param letter character of the tile
     * @param value  of the tile
     */
    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    /**
     * Copy constructor (same id, bonus is not conserved)
     *
     * @param t
     */
    public Tile(Tile t) {
        this.letter = t.letter;
        this.value = t.value;
    }

    public static String tilesToString(List<Tile> list) {
        String temp = "";
        for (Tile t : list) {
            temp += t.letter;
        }
        return temp;
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
     * Returns if the letter is a bonus letter
     *
     * @return boolean
     */
    public boolean isBonus() {
        return bonus;
    }

    /**
     * Set the letter as a bonus letter or not
     *
     * @param bonus
     */
    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    /**
     * Returns a String representing the Tile.java class.
     * The format is "Letter, Value"
     *
     * @return a String representing the Tile.java class
     */
    public String toString() {
        return getLetter() + ", " + getValue();
    }

    /**
     * compare a Tile with an Object and
     * return true if they have same fields and Class
     *
     * @param o Compared Object
     * @return true if the two Object is of same class and have the same fields
     */
    public boolean equals(Object o) {
        return getClass().isInstance(o) &&
                getClass() == o.getClass() &&
                bonus == ((Tile) o).bonus &&
                letter == ((Tile) o).letter &&
                value == ((Tile) o).value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.letter;
        hash = 53 * hash + this.value;
        hash = 53 * hash + (this.bonus ? 1 : 0);
        return hash;
    }
}
