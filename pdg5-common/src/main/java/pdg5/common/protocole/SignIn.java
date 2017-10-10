package pdg5.common.protocole;

import java.awt.Image;

/**
 * Class use to login into our application
 *
 * @author Maxime Guillod
 */
public class SignIn implements IServerRequest {

    private String login;
    private String password;

    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
