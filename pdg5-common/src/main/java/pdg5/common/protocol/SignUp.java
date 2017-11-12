package pdg5.common.protocol;

import java.awt.*;
import java.io.Serializable;

/**
 * Class use to register
 *
 * @author Maxime Guillod
 */
public class SignUp extends Message {

    private String email;
    private String username;
    private String password;

    public SignUp(String email, String login, String password) {
        this.email = email;
        this.username = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}