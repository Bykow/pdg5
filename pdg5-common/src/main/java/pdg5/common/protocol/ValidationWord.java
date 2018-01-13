package pdg5.common.protocol;

/**
 * Class sent through the connection by the server representing the answer to a
 * Validation request wich explain to a client if a word is valid or not.
 */
public class ValidationWord extends Message {

    /**
     * Answer to a Validation.java request
     */
    private boolean isValid;

    /**
     * Constructor
     *
     * @param isValid boolean representing the answer : is the word valid?
     */
    public ValidationWord(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * return a boolean representing if the word valid?
     *
     * @return a boolean representing if the word valid?
     */
    public boolean isIsValid() {
        return isValid;
    }

}
