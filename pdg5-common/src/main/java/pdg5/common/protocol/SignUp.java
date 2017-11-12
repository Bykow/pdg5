package pdg5.common.protocol;

import java.awt.Image;

/**
 * Class sended through the connection by a client representing
 * an attempt to sign up by a client.
 */
public class SignUp implements IServerRequest {

    private String login;
    private String password;
    private Image image;

    /**
     * Constructor
     * @param login trying to sign up
     * @param password trying to sign up
     * @param image used as profil
     */
    public SignUp(String login, String password, Image image) {
        this.login = login;
        this.password = password;
        this.image = image;
    }

    /**
     * return a profil image
     * @return a profil image
     */
    public Image getImage() {
        return image;
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
     * Try to create a new account in the server stockage 
     * with the password, login and image given
     */
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
