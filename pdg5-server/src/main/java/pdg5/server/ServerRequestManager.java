package pdg5.server;

import pdg5.common.protocol.SignIn;
import pdg5.common.protocol.SignUp;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    public void execute(Object o) {

        o.getClass();

        if (o instanceof SignUp) {
            signUp((SignUp) o);
        } else if (o instanceof SignIn) {
            signIn((SignIn) o);
        } else {
            System.err.println("Unknow type");
        }
    }

    private void signIn(SignIn signIn) {
        // TODO Implement signIn
        System.out.println(signIn);
    }

    private void signUp(SignUp signUp) {
        // TODO Implement signUp
        System.out.println(signUp);
    }

}
