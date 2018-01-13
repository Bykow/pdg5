/**
 * -----------------------------------------------------------------------------------
 * Laboratoire : <nn>
 * Fichier     : SignInController.java
 * Auteur(s)   : Andrea Cotza
 * Date        : 06.11.2017
 * <p>
 * But         : <‡ complÈter>
 * <p>
 * Remarque(s) : <‡ complÈter>
 * <p>
 * Compilateur : jdk1.8.0_60
 * -----------------------------------------------------------------------------------
 */

package pdg5.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.ClientListener;
import pdg5.client.ClientSender;
import pdg5.client.util.Toast;
import pdg5.client.util.UserInformations;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignIn;
import pdg5.common.protocol.SignInOK;

import java.io.IOException;

/**
 * SignIn Controller
 */
public class SignInController extends AbstractController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink btnSignup;

    private Scene signUpScene;
    private ClientSender sender;
    private ClientListener listener;

    @FXML
    public void initialize() {}

    public SignInController() {
        this.sender = new ClientSender();
        this.listener = new ClientListener();
    }

    /**
     * Behaviour when login button is pressed
     *
     * @param actionEvent
     */
    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogin.getScene().getWindow();

        SignIn signIn = new SignIn(username.getText(), password.getText());

        // Sends the info from the input field
        sender.add(signIn);

        // Waits for response of server
        Message msg = listener.take();
        if (msg instanceof ErrorMessage) {
            // Login is not valid
            // Empty password field
            password.setText("");

            new Toast(stage, "Incorrect username or password").show();

        } else if (msg instanceof SignInOK) {
            System.out.println(msg);
            // Saves the username into singleton
            UserInformations.getInstance().setUsername(username.getText());
            stage.hide();
            // Loads next stage
            loadProg(stage);
        }
    }

    /**
     * Behaviour when switching from signin to signup
     *
     * @param actionEvent
     */
    @FXML
    private void handleSwitch(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogin.getScene().getWindow();

        if (signUpScene == null) {
            Scene currentScene = btnLogin.getScene();

            // Creates the new scene
            try {
                FXMLLoader loader = new FXMLLoader();
                SignUpController controller = new SignUpController(currentScene, sender, listener);
                loader.setLocation(SignInController.class.getResource("/fxml/registerView.fxml"));
                loader.setController(controller);
                AnchorPane rootLayout = loader.load();

                signUpScene = new Scene(rootLayout);

                stage.setScene(signUpScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(signUpScene);
    }
}
