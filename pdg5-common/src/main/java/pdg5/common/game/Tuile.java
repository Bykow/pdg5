package pdg5.common.game;

/**
 * Created on 03.10.17 by Bykow
 */
public class Tuile {
    private char letter;
    private int value;

    public Tuile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public char getLetter() {
        return letter;
    }
}
