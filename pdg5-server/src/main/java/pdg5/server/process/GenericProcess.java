package pdg5.server.process;

import pdg5.common.protocol.Message;

/**
 * parent class of process so they have to implements the execute method
 */
public interface GenericProcess {

   /**
    * method to implement so the server know what to do when he receiv a message
    * 
    * @return the answer Message built by the server, Noop for an empty Message
    */
    public Message execute();
}
