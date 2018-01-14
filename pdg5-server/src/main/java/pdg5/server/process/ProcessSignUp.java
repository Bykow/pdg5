package pdg5.server.process;

import pdg5.common.Protocol;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.persistent.User;
import pdg5.server.util.ClientAlreadyConnected;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * class built by the server when he receive the request SignUp
 * Then the server wil try to sign up the client who sent this request
 */
public class ProcessSignUp implements GenericProcess {

    /**
     * the original SignUp request received
     */
    private final SignUp signUp;

    /**
     * to store/get users datas in the database
     */
    private final ManageUser manager;

    /**
     * manager of the user who are connected
     */
    private final ServerActiveUser activeUser;

    /**
     * manager of the manager of the socket where we received the SignIn
     */
    private final ClientHandler clientHandler;

    /**
     * Constructor
     *
     * @param signUp        the original SignUp request received
     * @param manageUser    to store/get users datas in the database
     * @param activeUser    manager of the user who are connected
     * @param clientHandler manager of the manager of the socket where we received the SignIn
     */
    public ProcessSignUp(SignUp signUp, ManageUser manageUser, ServerActiveUser activeUser, ClientHandler clientHandler) {
        this.signUp = signUp;
        this.manager = manageUser;
        this.activeUser = activeUser;
        this.clientHandler = clientHandler;
    }

    @Override
    public Message execute() {
        int exitCode;
        User userName = manager.getUserByUsername(signUp.getUsername());
        User userMail = manager.getUserByEmail(signUp.getEmail());

        if (userName != null) {
            return new ErrorMessage("The username is already used");
        }

        if (userMail != null) {
            return new ErrorMessage("The user email is already used");
        }

        User user = manager.addUser(signUp.getEmail(), signUp.getUsername(), signUp.getPassword());
        if (user != null) {
            exitCode = Protocol.OK;
        } else {
            exitCode = Protocol.ERROR;
        }

        System.out.println(exitCode);

        switch (exitCode) {
            case Protocol.OK:
                if (user != null) {
                    try {
                        activeUser.add(user.getId(), clientHandler);
                    } catch (ClientAlreadyConnected ex) {
                        return new ErrorMessage(ex.getMessage());
                    }
                    clientHandler.setPlayerId(user.getId());
                }
                clientHandler.addToQueue(new SignInOK());
                //clientHandler.addToQueue(new Dictionnary(gameController.getDictionnary()));
                return new Load();
            case Protocol.ERROR:
                return new ErrorMessage("Unexpected ErrorMessage in SignUp code: " + exitCode);
            case Protocol.COULDNOTADDUSER:
                return new ErrorMessage("Server could not add user in DataBase. Code: " + exitCode);
            default:
                return new ErrorMessage("Unhandled ErrorMessage in SignUp, default reached");
        }
    }
}
