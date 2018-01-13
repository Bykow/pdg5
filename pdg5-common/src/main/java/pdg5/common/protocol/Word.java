package pdg5.common.protocol;

import java.io.Serializable;

/**
 * Class representing a Word.
 */
public class Word implements Serializable {

    private String word;
    private Play play;
    private Validation validation;
    private int gameId;

    /**
     * Constructor
     *
     * @param word       String representing the word
     * @param play       associated play to the word, can be null.
     * @param validation associated validation to the word, can be null.
     * @param gameId     unique ID where the word context is.
     */
    public Word(String word, Play play, Validation validation, int gameId) {
        this.word = word;
        this.play = play;
        this.validation = validation;
        this.gameId = gameId;
    }

    /**
     * Return The unique ID of the game where is the word.
     *
     * @return The unique ID of the game where is the word.
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * return the associated play to the word.
     *
     * @return the associated play to the word.
     */
    public Play getPlay() {
        return play;
    }

    /**
     * return the associated Validation.
     *
     * @return the associated Validation.
     */
    public Validation getValidation() {
        return validation;
    }

    /**
     * return the word.
     *
     * @return the word.
     */
    public String getWord() {
        return word;
    }

}
