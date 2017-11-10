package pdg5.server.util;

import pdg5.common.protocol.*;
import pdg5.server.process.ProcessSignIn;
import pdg5.server.process.ProcessSignUp;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    /**
     * @param o
     * @return Message to be send
     */
    public Message execute(Object o) {

        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o).execute();

        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o).execute();

        } else if(o instanceof Noop) {
            return new Noop();
        }

        return new ErrorMessage("Unhandled ErrorMessage is ServerRequestManager, default reached");

    }

}
