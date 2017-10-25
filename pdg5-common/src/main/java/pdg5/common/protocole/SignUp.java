package pdg5.common.protocole;

import java.awt.Image;

/**
 * Class sended through the connection representing
 * a attempt to sign up by a client.
 */
public class SignUp implements IServerRequest {

    private String login;
    private String password;
    private Image image;

    /**
     * Constructor
     * @param login
     * @param password
     * @param image 
     */
    public SignUp(String login, String password, Image image) {
        this.login = login;
        this.password = password;
        this.image = image;
    }

    /**
     * @return a profil images
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return a password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
