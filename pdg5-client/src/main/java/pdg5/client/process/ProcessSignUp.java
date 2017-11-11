package pdg5.client.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignUp;

/**
 * @author Maxime Guillod
 */
public class ProcessSignUp implements GenericProcess {

    private SignUp signUp;

    public ProcessSignUp(SignUp signUp) {
        this.signUp = signUp;
    }

    @Override
    public Message execute() {
        return null;
    }
}
