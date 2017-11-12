package pdg5.common.protocol;

import java.io.Serializable;

/**
 * Class sended through the connection by a client
 * to ask for a validation of a word by the server.
 */
public class Validation extends Message {

   /**
    * Constructor
    */
    public Validation() {
    }

}
