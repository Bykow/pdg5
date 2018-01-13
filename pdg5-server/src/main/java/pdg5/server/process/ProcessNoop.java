package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;

public class ProcessNoop implements GenericProcess {

    private Noop noop;

    public ProcessNoop(Noop noop) {
        this.noop = noop;
    }

    @Override
    public Message execute() {
        if (noop.getSender() == Noop.Sender.CLIENT) {
            System.out.println("ProcessNoop.execute Sender=CLIENT");
            return noop;
        }
        return null;
    }
}
