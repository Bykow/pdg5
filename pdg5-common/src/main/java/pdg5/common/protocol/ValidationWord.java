package pdg5.common.protocol;

import java.io.Serializable;

/**
 * Class sended through the connection by the server representing
 * the answer to a Validation request wich explain to a client 
 * if a word is valid or not.
 */
public class ValidationWord extends Message {

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

}
