package pdg5.common.protocole;

/**
 *
 * @author Maxime Guillod
 */
public class ValidationWord implements IClientRequest {

    private boolean isValid;

    public ValidationWord(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isIsValid() {
        return isValid;
    }

    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}