package pdg5.client.process;

import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;

public class ProcessErrorMessage implements GenericProcess {

    private ErrorMessage message;

    public ProcessErrorMessage(ErrorMessage message) {
        this.message = message;
    }

    @Override
    public Message execute() {
        // TODO toast
        System.out.println("ErrorMessage : " + message.getError());
        return new Noop(Noop.Sender.CLIENT);
    }
    
}
