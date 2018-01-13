/**
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : SignInController.java
 Auteur(s)   : Andrea Cotza
 Date        : 06.11.2017

 But         : <‡ complÈter>

 Remarque(s) : <‡ complÈter>

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
 */

package pdg5.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pdg5.client.ClientListener;
import pdg5.client.ClientSender;
import pdg5.client.util.Toast;
import pdg5.client.util.UserInformations;
import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignInOK;
import pdg5.common.protocol.SignUp;

public class SignUpController extends AbstractController {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConf;
    @FXML
    private Button btnRegister;
    @FXML
    private Hyperlink switchLogin;

    private Scene signInScene;
    private ClientSender sender;
    private ClientListener listener;

    SignUpController(Scene signInScene, ClientSender sender, ClientListener listener) {
        this.signInScene = signInScene;
        this.sender = sender;
        this.listener = listener;
    }

    @FXML
    public void initialize() {
    }

    /**
     * Behaviour when switching from signup to signin
     *
     * @param actionEvent
     */
    @FXML
    private void handleSwitch(ActionEvent actionEvent) {
        Stage stage = (Stage)btnRegister.getScene().getWindow();
        stage.setScene(signInScene);
    }

    /**
     * Behaviour when register button pressed
     *
     * @param actionEvent
     */
    @FXML
    private void handleRegister(ActionEvent actionEvent) {
        Stage stage = (Stage)btnRegister.getScene().getWindow();
        boolean isSent = false;

        // Basic check if both password and confirmation are equals
        if (checkPassword(password.getText(), passwordConf.getText())) {
            // Sending to server
            SignUp signUp = new SignUp(email.getText(), username.getText(), password.getText());
            sender.add(signUp);
            isSent = true;
        } else {
            new Toast(stage, "Password and password confirmation are not equal, try again.").show();
            password.clear();
            passwordConf.clear();
        }

        // Wait for response
        if(isSent) {
            Message msg = listener.take();

            if (msg instanceof ErrorMessage) {
                System.err.println(msg);
                password.setText("");
                passwordConf.setText("");

                new Toast(stage, msg.toString()).show();
            } else if (msg instanceof SignInOK) {
                System.out.println("SignUp success ");
                // Saves the username and email into Singleton
                UserInformations.getInstance().setUsername(username.getText());
                UserInformations.getInstance().setMail(email.getText());
                stage.hide();
                // Load next stage
                loadProg(stage);
            }
        }
    }

    private boolean checkPassword(String a, String b) {
        return a.equals(b);
    }

}
