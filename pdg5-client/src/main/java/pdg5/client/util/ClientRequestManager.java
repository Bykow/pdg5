package pdg5.client.util;


import pdg5.client.process.ProcessErrorMessage;
import pdg5.client.process.ProcessLoad;
import pdg5.client.process.ProcessNoop;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;

/**
 * @author Maxime Guillod
 */
public class ClientRequestManager {

    public Message execute(Message o) {
        if (o instanceof ErrorMessage) {
            return new ProcessErrorMessage((ErrorMessage) o).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();

        } else if (o instanceof  Load) {
            return new ProcessLoad((Load) o).execute();
        }

        return new ErrorMessage("Unable to check Message Type");
    }

}
