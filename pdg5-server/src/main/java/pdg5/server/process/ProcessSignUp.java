package pdg5.server.process;

import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.common.Protocol;
import pdg5.common.WordCombinations;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.persistent.User;
import pdg5.server.util.ClientAlreadyConnected;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignUp implements GenericProcess {

    private final SignUp signUp;
    private GameController gameController;
    private final ManageUser manager;
    private final ServerActiveUser activeUser;
    private final ClientHandler clientHandler;

    public ProcessSignUp(SignUp signUp, GameController gameController, ManageUser manageUser, ServerActiveUser activeUser, ClientHandler clientHandler) {
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
        
        if(userName != null){
            return new ErrorMessage("The username is already used");
        }
        
        if(userMail != null){
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
