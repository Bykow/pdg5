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

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
