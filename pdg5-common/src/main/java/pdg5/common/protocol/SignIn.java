package pdg5.common.protocol;

/**
 * Class use to login into our application
 *
 * @author Maxime Guillod
 */
public class SignIn extends Message {

   /**
    * username trying to log in
    */
    private String username;
    
    /**
    * password trying to log in
    */
    private String password;

    /**
     * Constructor
     * 
     * @param username used to try to log in
     * @param password used to try to log in
     */
    public SignIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * return the username used to log in
     * @return the username used to log in
     */
    public String getUsername() {
        return username;
    }

    /**
     * return the password used to log in
     * 
     * @return the password used to log in
     */
    public String getPassword() {
        return password;
    }

    /**
     * return a string representation of this instance
     * 
     * @return a string representation of this instance
     */
    @Override
    public String toString() {
        return "SignIn{" + "Username=" + username + '}';
    }

}
