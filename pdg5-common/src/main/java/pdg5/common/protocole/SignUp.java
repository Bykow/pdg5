package pdg5.common.protocole;

import java.awt.Image;

/**
 * Class use to register
 *
 * @author Maxime Guillod
 */
public class SignUp implements IServerRequest {

    private String login;
    private String password;
    private Image image;

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
