package pdg5.common.game;

import java.io.Serializable;

/**
 * Created on 03.10.17 by Bykow
 * A Tile represents a letter and its value on in the game. The value is
 * directly linked to the langage of the game
 */
public class Tile implements Serializable {
    private char letter;
    private int value;

    /**
     * Ctor
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

    public String toString() {
        return getLetter() + ", " + getValue();
    }
}
