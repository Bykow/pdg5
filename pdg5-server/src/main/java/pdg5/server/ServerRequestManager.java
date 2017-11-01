package pdg5.server;

import pdg5.common.protocol.*;
import pdg5.server.process.ProcessSignIn;
import pdg5.server.process.ProcessSignUp;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    /**
     *
     * @param o
     * @return Message to be send
     */
    public Message execute(Object o) {

        if (o instanceof SignUp) {
            new ProcessSignUp((SignUp) o).execute();

        } else if (o instanceof SignIn) {
            new ProcessSignIn((SignIn) o).execute();

        } else {
            System.err.println("Unknow type");
        }

        return null;
    }

}
