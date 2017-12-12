package pdg5.common.protocol;

/**
 * Class use to login into our application
 *
 * @author Maxime Guillod
 */
public class SignIn extends Message {

    private String username;
    private String password;

    public SignIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SignIn{" + "Username=" + username + ", password=" + password + '}';
    }

}
