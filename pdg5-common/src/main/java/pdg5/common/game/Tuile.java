package pdg5.common.game;

/**
 * Created on 03.10.17 by Bykow
 * A Tuile represents a letter and its value on in the game. The value is
 * directly linked to the langage of the game
 */
public class Tuile {
    private char letter;
    private int value;

    /**
     * Ctor
     *
     * @param letter
     * @param value
     */
    public Tuile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    /**
     * Returns the letter of a Tuile
     *
     * @return char letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Returns the value of a letter on a Tuile
     *
     * @return int value
     */
    public int getValue() {
        return value;
    }
}
