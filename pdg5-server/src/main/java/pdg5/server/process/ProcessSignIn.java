package pdg5.server.process;

import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignIn;
import pdg5.server.manage.ManageGame;
import pdg5.server.manage.ManageUser;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignIn implements GenericProcess {
    private SignIn signIn;
    private ManageUser manageUser;
    private ManageGame manageGame;

    public ProcessSignIn(SignIn signIn) {
        this.signIn = signIn;
        this.manageUser = manageUser;
    }

    @Override
    public Message execute() {
        if(manageUser.isCorrectPassword(signIn.getUsername(), signIn.getPassword())) {
            return new Load(manageGame(signIn.getUsername()));
        } else {
            return new ErrorMessage("Password invalid in SignIn for user " + signIn.getUsername());
        }
    }
}
