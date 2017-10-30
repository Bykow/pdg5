package pdg5.common.protocole;

import java.io.Serializable;

/**
 * Class use to login into our application
 *
 * @author Maxime Guillod
 */
public class SignIn implements IServerRequest, Serializable {

    private String login;
    private String password;

    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void execute() {
        System.out.println("pdg5.common.protocole.SignIn.execute()");
        System.out.println(getPassword());
    }

    @Override
    public String toString() {
        return "SignIn{" + "login=" + login + ", password=" + password + '}';
    }

}
