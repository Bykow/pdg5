package pdg5.server;

import pdg5.common.protocol.*;

/**
 * @author Maxime Guillod
 */
public class ServerRequestManager {

    public void execute(Object o) {

        if (o instanceof SignUp) {
            new ProcessSignUp((SignUp) o).execute();

        } else if (o instanceof SignIn) {
            new ProcessSignIn((SignIn) o).execute();

        } else if (o instanceof Validation) {
            new ProcessValidation((Validation) o).execute();

        } else if (o instanceof Word) {
            new ProcessWord((Word) o).execute();

        } else if (o instanceof Chat) {
            new ProcessChat((Chat) o).execute();

        } else {
            System.err.println("Unknow type");
        }
    }

}
