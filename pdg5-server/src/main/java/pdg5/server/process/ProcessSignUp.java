package pdg5.server.process;

import pdg5.common.Protocol;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.util.ServerActiveUser;

import java.util.List;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignUp implements GenericProcess {
    private SignUp signUp;
    private ManageUser manager;
    private ServerActiveUser activeUser;

    public ProcessSignUp(SignUp signUp, ManageUser manageUser, ServerActiveUser activeUser) {
        this.signUp = signUp;
        this.manager = manageUser;
        this.activeUser = activeUser;
    }

    @Override
    public Message execute() {
        int exitCode;
        if (manager.addUser(signUp.getEmail(), signUp.getUsername(), signUp.getPassword()) != null) {
            exitCode = Protocol.OK;
        } else {
            exitCode = Protocol.ERROR;
        }

        switch (exitCode) {
            case Protocol.OK :
                return new Load();
                //todo this is not suppose to be empty, waiting for game logic to continue
            case Protocol.ERROR :
                return new ErrorMessage("Unexpected ErrorMessage in SignUp code: " + exitCode);
            case Protocol.COULDNOTADDUSER :
                return new ErrorMessage("Server could not add user in DataBase. Code: " + exitCode);
            default :
                return new ErrorMessage("Unhandled ErrorMessage in SignUp, default reached");
        }
       //return new ErrorMessage("Not implemented");
    }
}
