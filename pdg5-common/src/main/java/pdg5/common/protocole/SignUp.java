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

    public SignUp(String login, String password, Image image) {
        this.login = login;
        this.password = password;
        this.image = image;
    }

    public Image getImage() {
        return image;
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
