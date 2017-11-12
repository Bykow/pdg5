package pdg5.common.protocol;


/**
 * Interface that object sended by the server to the client needs to implements. 
 * In this way, the client will understand the exchange and be able to react 
 * in a correct way by calling the method clientExecute().
 */
public interface IClientRequest {

   /**
    * method called when the client receive the class implementing this class.
    */
    public void clientExecute();
    
}
