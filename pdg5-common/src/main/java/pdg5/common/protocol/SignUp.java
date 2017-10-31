package pdg5.common.protocol;

import java.awt.*;
import java.io.Serializable;

/**
 * Class use to register
 *
 * @author Maxime Guillod
 */
public class SignUp implements Serializable {

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

}
