package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;

/**
 * Process who does nothing, allow to stop a request-answer-request-etc chain
 */
public class ProcessNoop implements GenericProcess {

   /**
    * the original Noop received
    */
    private Noop noop;

    /**
     * Constructor
     * 
     * @param noop the original Noop received
     */
    public ProcessNoop(Noop noop) {
        this.noop = noop;
    }

    /**
     * does nothing but print a sentence for debug
     * 
     * @return null, or send back the Noop to the client
     */
    @Override
    public Message execute() {
        if (noop.getSender() == Noop.Sender.CLIENT) {
            System.out.println("ProcessNoop.execute Sender=CLIENT");
            return noop;
        }
        return null;
    }
}
