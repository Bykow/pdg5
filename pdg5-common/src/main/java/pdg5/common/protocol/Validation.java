package pdg5.common.protocol;

import java.io.Serializable;
import pdg5.common.game.Composition;

/**
 * Class sended through the connection by a client
 * to ask for a validation of a word by the server.
 */
public class Validation extends Message {

   /**
    * the composition that the client want to validate
    */
   private final Composition composition;
   
   /**
    * unique id of the client who wants to validate a Composition
    */
   private final int idClient;
   
   /**
    * Constructor
    * 
    * @param composition the composition that the client want to validate
    */
    public Validation(Composition composition, int idClient) {
       this.composition = composition;
       this.idClient = idClient;
    }

    /**
     * return the composition that the client want to validate
     * 
     * @return the composition that the client want to validate
     */
   public Composition getComposition() {
      return composition;
   }
   
   /**
     * return the unique id of the player who is loading games
     * @return the unique id of the player who is loading games
     */
   public int getIdClient() {
      return idClient;
   }

    
}
