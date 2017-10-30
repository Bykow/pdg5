package pdg5.common.protocole;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class ValidationWord implements Serializable {

    private boolean isValid;

    public ValidationWord(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isIsValid() {
        return isValid;
    }

}
