package pdg5.common.protocol;

import java.io.Serializable;

/**
 * Class sended through the connection by a client representing
 * a attempt to sign in by a client.
 */
public class SignIn implements Serializable {

    private String login;
    private String password;

    /**
     * Constructor
     * @param login trying to sign in
     * @param password trying to sign in
     */
    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * return the login
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * return the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method immediately used when the server receive the object.
     * The server will try to authenticate an anonymous client
     * with the given login and password.
     * If it succes, the client will be logged and increase in privileges.
     */
    @Override
    public String toString() {
        return "SignIn{" + "login=" + login + ", password=" + password + '}';
    }

}
