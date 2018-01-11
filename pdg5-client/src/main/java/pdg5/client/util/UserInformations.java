package pdg5.client.util;

/**
 * Created on 10.01.18 by Bykow
 */
public class UserInformations {
    private String username;
    private String mail;
    private int idGameDisplayed;

    /** Constructeur privé */
    private UserInformations()
    {}

    /** Instance unique non préinitialisée */
    private static UserInformations INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static UserInformations getInstance()
    {
        if (INSTANCE == null) {
            INSTANCE = new UserInformations();
        }
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String temp;
        temp = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
        this.username = temp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public int getIdGameDisplayed() {
        return idGameDisplayed;
    }

    public void setIdGameDisplayed(int idGameDisplayed) {
        this.idGameDisplayed = idGameDisplayed;
    }
}
