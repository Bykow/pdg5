package pdg5.common.protocole;

/**
 * Class sended through the connection by the server representing
 * the answer to a Validation request wich explain to a client 
 * if a word is valid or not.
 */
public class ValidationWord implements IClientRequest {

   //Answer to a Validation.java request
    private boolean isValid;

    /**
     * Constructor
     * @param isValid boolean representing the answer : is the word valid?
     */
    public ValidationWord(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * Return a boolean representing the answer : is the word valid?
     * @return a boolean representing the answer : is the word valid?
     */
    public boolean isIsValid() {
        return isValid;
    }

    /**
     * Method immediately used when the client receive the object.
     * The client will be informed if his word is valid 
     * and then adapt his UI to the answer.
     */
    @Override
    public void clientExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
