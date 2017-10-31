package pdg5.common.protocol;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class ValidationWord extends Message {

    private boolean isValid;

    public ValidationWord(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isIsValid() {
        return isValid;
    }

}
