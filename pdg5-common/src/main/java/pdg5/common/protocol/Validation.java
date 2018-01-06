package pdg5.common.protocol;

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
    * Constructor
    * 
    * @param composition the composition that the client want to validate
    */
    public Validation(Composition composition) {
       this.composition = composition;
    }

    /**
     * return the composition that the client want to validate
     * 
     * @return the composition that the client want to validate
     */
   public Composition getComposition() {
      return composition;
   }
}
