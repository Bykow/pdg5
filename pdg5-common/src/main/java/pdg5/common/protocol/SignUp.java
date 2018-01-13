package pdg5.common.protocol;

/**
 * Class use to register
 */
public class SignUp extends Message {

   /**
    * email of the sign up
    */
    private String email;
    
    /**
     * username of the sign up
     */
    private String username;
    
    /**
     * password of the sign up
     */
    private String password;

    /**
     * Constructor
     * 
     * @param email used to sign up
     * @param username used to sign up
     * @param password used to sign up
     */
    public SignUp(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * return the email used to sign up
     * 
     * @return the email used to sign up
     */
    public String getEmail() {
        return email;
    }

    /**
     * return the username used to sign up
     * 
     * @return the username used to sign up
     */
    public String getUsername() {
        return username;
    }

    /**
     * return the password used to sign up
     * 
     * @return the password used to sign up
     */
    public String getPassword() {
        return password;
    }

    /**
     * override the print of this class for easier debug in client
     * 
     * @return a string with the email and username used to sign up
     */
   @Override
   public String toString() {
      return "SignUp{" + "email=" + email + ", username=" + username + '}';
   }
}
