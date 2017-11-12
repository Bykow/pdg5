package pdg5.common.protocol;

import java.awt.Image;
import java.io.Serializable;

/**
 * Class use to login into our application
 *
 * @author Maxime Guillod
 */
public class SignIn extends Message {

    private String username;
    private String password;

    /**
     * Constructor
     * @param username trying to sign in
     * @param password trying to sign in
     */
    public SignIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * return the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * return the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SignIn{" + "Username=" + username + ", password=" + password + '}';
    }

}
