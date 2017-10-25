package pdg5.common.protocole;

import java.awt.Image;

/**
 * Class sended through the connection representing
 * a attempt to sign in by a client.
 */
public class SignIn implements IServerRequest {

    private String login;
    private String password;

    /**
     * Constructor
     * @param login
     * @param password 
     */
    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method immediately used when the server or client receive the object.
     * The server will try to authenticate an anonymous client
     * with the given login and password.
     * If it succes, the client will be logged and increase in privileges.
     */
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
