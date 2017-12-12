package pdg5.server.util;

import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.process.*;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    private ManageUser manageUser;
    private ServerActiveUser activeUser;
    private GameController gameController;

    public ServerRequestManager(ServerActiveUser activeUser) {
        this.manageUser = new ManageUser();
        this.activeUser = activeUser;
        this.gameController = new GameController();
    }

    /**
     * @param o
     * @return Message to be send
     */
    public Message execute(Message o) {
        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o, manageUser, activeUser).execute();

        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o, manageUser, activeUser, gameController).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();
            
        } else if (o instanceof NewGame) {
           return new ProcessNewGame((NewGame) o, gameController).execute();
           
        } else if (o instanceof Validation) {
           return new ProcessValidation((Validation) o, gameController).execute();
           
        } else if (o instanceof Play) {
           return new ProcessPlay((Play) o, gameController).execute();
           
        }

        return new ErrorMessage("Unhandled ErrorMessage is ServerRequestManager, default reached");
    }

}
