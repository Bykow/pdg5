package pdg5.common.game;

import java.io.Serializable;

/**
 * Created on 03.10.17 by Bykow
 */
public class Tuile implements Serializable {
    private char letter;
    private int value;

    public Tuile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
}
