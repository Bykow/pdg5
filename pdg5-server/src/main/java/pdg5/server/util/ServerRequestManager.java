package pdg5.server.util;

import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignIn;
import pdg5.common.protocol.SignUp;
import pdg5.server.manage.ManageUser;
import pdg5.server.process.ProcessSignIn;
import pdg5.server.process.ProcessSignUp;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {
    private ManageUser manageUser;

    public ServerRequestManager() {
        this.manageUser = new ManageUser();
    }

    /**
     * @param o
     * @return Message to be send
     */
    public Message execute(Object o) {

        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o, manageUser).execute();
        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o, manageUser).execute();
        }

        return new ErrorMessage("Unhandled ErrorMessage is ServerRequestManager, default reached");

    }

}
