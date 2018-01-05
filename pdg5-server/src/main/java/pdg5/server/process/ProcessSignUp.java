package pdg5.server.process;

import pdg5.common.Protocol;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignUp;
import pdg5.server.manage.ManageUser;
import pdg5.server.persistent.User;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignUp implements GenericProcess {

    private final SignUp signUp;
    private final ManageUser manager;
    private final ServerActiveUser activeUser;
    private final ClientHandler clientHandler;

    public ProcessSignUp(SignUp signUp, ManageUser manageUser, ServerActiveUser activeUser, ClientHandler clientHandler) {
        this.signUp = signUp;
        this.manager = manageUser;
        this.activeUser = activeUser;
        this.clientHandler = clientHandler;
    }

    @Override
    public Message execute() {
        int exitCode;
        User user = manager.addUser(signUp.getEmail(), signUp.getUsername(), signUp.getPassword());
        if (user != null) {
            exitCode = Protocol.OK;
        } else {
            exitCode = Protocol.ERROR;
        }

        switch (exitCode) {
            case Protocol.OK:
                if (user != null) {
                    activeUser.add(user.getId(), clientHandler);
                    clientHandler.setPlayerId(user.getId());
                }
                return new Load();
            // TODO this is not suppose to be empty, waiting for game logic to continue
            case Protocol.ERROR:
                return new ErrorMessage("Unexpected ErrorMessage in SignUp code: " + exitCode);
            case Protocol.COULDNOTADDUSER:
                return new ErrorMessage("Server could not add user in DataBase. Code: " + exitCode);
            default:
                return new ErrorMessage("Unhandled ErrorMessage in SignUp, default reached");
        }
        //return new ErrorMessage("Not implemented");
    }
}
