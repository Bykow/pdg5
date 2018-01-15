package pdg5.server.util;

import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.process.*;

/**
 * class who handle the requests comming from clients
 * she reacts, bluids correct classes and calls the correct methods
 * depending on the request type
 */
public class ServerRequestManager {

    /**
     * to store/get users datas in the database
     */
    private final ManageUser manageUser;

    /**
     * manager of the user who are connected
     */
    private final ServerActiveUser activeUser;

    /**
     * where the games and game logique are store
     */
    private final GameController gameController;

    /**
     * Constructor
     *
     * @param activeUser     manager of the user who are connected
     * @param gameController where the games and game logique are store
     */
    public ServerRequestManager(ServerActiveUser activeUser, GameController gameController) {
        this.manageUser = new ManageUser();
        this.activeUser = activeUser;
        this.gameController = gameController;
    }

    /**
     * handle the requests comming from clients
     * she reacts, bluids correct classes and calls the correct methods
     * depending on the request type
     * <p>
     * Then send back to the client the answer in a protocol.Message child class
     *
     * @param o  the Message received
     * @param ch the ClientHandler associated at the client who sent the request
     * @return Message to be send
     */
    public Message execute(Message o, ClientHandler ch) {
        System.out.println("Message received : " + o);

        if (o instanceof SignUp) {
            return new ProcessSignUp((SignUp) o, manageUser, activeUser, ch).execute();

        } else if (o instanceof SignIn) {
            return new ProcessSignIn((SignIn) o, manageUser, activeUser, gameController, ch).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();

        } else if (ch.isConnected()) {
            if (o instanceof NewGame) {
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

        System.err.println("Unhandled ErrorMessage is ServerRequestManager, default reached are you connected?");
        return new Noop(Noop.Sender.SERVER);
    }

}
