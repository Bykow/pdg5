package pdg5.client.util;

import pdg5.client.process.ProcessNoop;
import pdg5.client.process.ProcessSignUp;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;
import pdg5.common.protocol.SignUp;

/**
 * @author Maxime Guillod
 */
public class ClientRequestManager {

    public Message execute(Message o) {
        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();
        }

        return new ErrorMessage("Unable to check Message Type");
    }

}
