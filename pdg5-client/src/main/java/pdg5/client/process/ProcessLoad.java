package pdg5.client.process;

import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;

public class ProcessLoad implements GenericProcess {

    private Load load;

    public ProcessLoad(Load load) {
        this.load = load;
    }

    @Override
    public Message execute() {
        return null;
    }
}
