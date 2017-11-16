package pdg5.server.util;

import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.process.ProcessNoop;
import pdg5.server.process.ProcessSignIn;
import pdg5.server.process.ProcessSignUp;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    private ManageUser manageUser;
    private ServerActiveUser activeUser;

    public ServerRequestManager(ServerActiveUser activeUser) {
        this.manageUser = new ManageUser();
        this.activeUser = activeUser;
    }

    /**
     * @param o
     * @return Message to be send
     */
    public Message execute(Message o) {
        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o, manageUser, activeUser).execute();

        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o, manageUser, activeUser).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();
        }

        return new ErrorMessage("Unhandled ErrorMessage is ServerRequestManager, default reached");
    }

}
