package pdg5.client.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;

/**
 * ProcessNoop, behaviour for client when receiving a Noop message
 * Noop is not an operation
 */
public class ProcessNoop implements GenericProcess {

    private Noop noop;

    /**
     * Ctor
     *
     * @param noop message noop
     */
    public ProcessNoop(Noop noop) {
        this.noop = noop;
    }

    /**
     * Execute the behaviour when receiving a Noop message
     *
     * @return noop
     */
    @Override
    public Message execute() {
        if (noop.getSender() == Noop.Sender.SERVER) {
            return noop;
        }
        return null;
    }
}
