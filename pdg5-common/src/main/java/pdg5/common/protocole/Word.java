package pdg5.common.protocole;

/**
 * A word which is enter by a player
 *
 * @author Maxime Guillod
 */
public class Word implements IServerRequest {

    private String word;
    private Play play;
    private Validation validation;
    private int gameId;

    public Word(String word, Play play, Validation validation, int gameId) {
        this.word = word;
        this.play = play;
        this.validation = validation;
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public Play getPlay() {
        return play;
    }

    public Validation getValidation() {
        return validation;
    }

    public String getWord() {
        return word;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}