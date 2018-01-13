package pdg5.server.util;

import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.process.*;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    private final ManageUser manageUser;
    private final ServerActiveUser activeUser;
    private final GameController gameController;

    public ServerRequestManager(ServerActiveUser activeUser, GameController gameController) {
        this.manageUser = new ManageUser();
        this.activeUser = activeUser;
        this.gameController = gameController;
    }

    /**
     * @param o
     * @param ch
     * @return Message to be send
     */
    public Message execute(Message o, ClientHandler ch) {
        System.out.println("Message received : " + o);

        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o, gameController, manageUser, activeUser, ch).execute();

        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o, manageUser, activeUser, gameController, ch).execute();

        } else if (ch.isConnected()) {
            if (o instanceof Noop) {
                return new ProcessNoop((Noop) o).execute();

            } else if (o instanceof NewGame) {
                return new ProcessNewGame((NewGame) o, gameController, activeUser, ch).execute();

            } else if (o instanceof Validation) {
                return new ProcessValidation((Validation) o, gameController).execute();

            } else if (o instanceof Play) {
                return new ProcessPlay((Play) o, gameController, ch).execute();

            } else if (o instanceof Friend) {
                return new ProcessFriend((Friend) o, manageUser, ch).execute();

            } else if (o instanceof Pass) {
                return new ProcessPass((Pass) o, gameController, ch).execute();

            } else if (o instanceof Chat) {
                return new ProcessChat((Chat) o, gameController, ch).execute();

            } else if (o instanceof Logout) {
                return new ProcessLogout((Logout) o, activeUser, ch).execute();

            }
        }

        return new ErrorMessage("Unhandled ErrorMessage is ServerRequestManager, default reached are you connected?");
    }

}
