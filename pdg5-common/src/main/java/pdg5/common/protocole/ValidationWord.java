package pdg5.common.protocole;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class ValidationWord implements Serializable {

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
