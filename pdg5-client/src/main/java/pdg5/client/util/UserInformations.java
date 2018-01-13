package pdg5.client.util;

/**
 * Small singleton to save user information across the program
 */
public class UserInformations {
    /**
     * Unique instance. Null by default
     */
    private static UserInformations INSTANCE = null;
    private String username;
    private String mail;
    private int idGameDisplayed;

    /**
     * Private Ctor
     */
    private UserInformations() {
    }

    /** Point d'accès pour l'instance unique du singleton */

    /**
     * Access to singleton
     *
     * @return UserInformations
     */
    public static UserInformations getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserInformations();
        }
        return INSTANCE;
    }

    /**
     * Default getter for username
     *
     * @return string username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Default setter for username, capitalizes the first letter
     *
     * @param username username to set
     */
    public void setUsername(String username) {
        String temp;
        temp = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
        this.username = temp;
    }

    /**
     * Default getter for mail after signup
     *
     * @return string mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Default setter for mail
     *
     * @param mail mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Default getter for id of game displayed
     *
     * @return int id
     */
    public int getIdGameDisplayed() {
        return idGameDisplayed;
    }

    /**
     * Default setter for id of game displayed
     *
     * @param idGameDisplayed id to set
     */
    public void setIdGameDisplayed(int idGameDisplayed) {
        this.idGameDisplayed = idGameDisplayed;
    }
}
