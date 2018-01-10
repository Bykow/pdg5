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

    SignUpController(Scene signInScene) {
        this.signInScene = signInScene;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    private void handleSwitch(ActionEvent actionEvent) {
        Stage stage = (Stage)btnRegister.getScene().getWindow();
        stage.setScene(signInScene);
    }

    @FXML
    private void handleRegister(ActionEvent actionEvent) {
        Stage stage = (Stage)btnRegister.getScene().getWindow();
        ClientSender clientSender = new ClientSender();
        ClientListener listener = new ClientListener();
        boolean isSent = false;


        if (checkPassword(password.getText(), passwordConf.getText())) {
            // Sending to server
            SignUp signUp = new SignUp(email.getText(), username.getText(), password.getText());
            clientSender.add(signUp);
            isSent = true;
        } else {
            System.out.println("Password and password confirmation are not equal, try again.");
            new Toast(stage, "Password and password confirmation are not equal, try again.").show();
            password.clear();
            passwordConf.clear();
        }

        if(isSent) {
            Message msg = listener.take();

            if (msg instanceof ErrorMessage) {
                System.err.println(msg);
                password.setText("");

                new Toast(stage, msg.toString()).show();
            } else if (msg instanceof SignInOK) {
                System.out.println("SignUp success ");
                UserInformations.getInstance().setUsername(username.getText());
                UserInformations.getInstance().setMail(email.getText());
                stage.hide();
                loadGame(stage);
            }
        }
    }

    private boolean checkPassword(String a, String b) {
        return a.equals(b);
    }

}
