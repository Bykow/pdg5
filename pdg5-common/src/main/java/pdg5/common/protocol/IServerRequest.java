package pdg5.common.protocol;


/**
 * Interface that object sended by the client to the server needs to implements. 
 * In this way, the server will understand the exchange and be able to react 
 * in a correct way by calling the method execute().
 */
public interface IServerRequest {

   /**
    * method called when the server receive the class implementing this class.
    */
    public void execute();

}
