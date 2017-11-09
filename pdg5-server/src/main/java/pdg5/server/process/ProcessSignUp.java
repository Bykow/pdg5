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

    public ProcessSignUp(SignUp signUp) {
        this.signUp = signUp;
        this.manager = new ManageUser();
    }

    @Override
    public Message execute() {
        return null;
//        int code = manager.addUser(signUp.getEmail(), signUp.getUsername(), signUp.getPassword());
//
//        switch (code) {
//            case Protocol.OK :
//                return new Load();
//            case Protocol.ERROR :
//                return new ErrorMessage("Unexpected ErrorMessage in SignUp code: " + Protocol.ERROR);
//            default :
//                return new ErrorMessage("Unhandled ErrorMessage in SignUp, default reached");
//        }
    }
}
