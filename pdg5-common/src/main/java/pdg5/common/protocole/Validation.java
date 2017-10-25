package pdg5.common.protocole;

/**
 * Class sended through the connection by a client
 * to ask for a validation of a word by the server.
 */
public class Validation implements IServerRequest {

   /**
    * Constructor
    */
    public Validation() {
       
    }

    /**
     * Method immediately used when the server receive the object.
     * The server will check the validity of the word and send back 
     * to the client the answer with ValidationWord.java class-request.
     */
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
