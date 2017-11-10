package pdg5.server.process;

import pdg5.common.Protocol;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;

import java.util.List;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignUp implements GenericProcess {
    private SignUp signUp;
    private ManageUser manager;

    public ProcessSignUp(SignUp signUp, ManageUser manageUser) {
        this.signUp = signUp;
        this.manager = manageUser;
    }

    @Override
    public Message execute() {
        manager.addUser(signUp.getEmail(), signUp.getUsername(), signUp.getPassword());

        //todo change that its just for demo
        int code = Protocol.OK;

        switch (code) {
            case Protocol.OK :
                return new Load();
                //todo this is not suppose to be empty
            case Protocol.ERROR :
                return new ErrorMessage("Unexpected ErrorMessage in SignUp code: " + Protocol.ERROR);
            default :
                return new ErrorMessage("Unhandled ErrorMessage in SignUp, default reached");
        }
    }
}
