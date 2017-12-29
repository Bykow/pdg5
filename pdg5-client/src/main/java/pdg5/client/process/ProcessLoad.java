package pdg5.client.process;

import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.NewGame;

public class ProcessLoad implements GenericProcess {

    private Load load;

    public ProcessLoad(Load load) {
        this.load = load;
    }

    @Override
    public Message execute() {
       System.out.println("after load, sending new game random");
       return new NewGame(1);
     }
}
