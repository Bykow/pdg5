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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pdg5.client.Client;
import pdg5.client.ClientSender;
import pdg5.common.protocol.SignUp;

import java.io.IOException;

public class SignUpController {

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
        ClientSender cs = new ClientSender();

        if (checkPassword(password.getText(), passwordConf.getText())) {
            // Sending to server
            SignUp signUp = new SignUp(email.getText(), username.getText(), password.getText());
            cs.addToQueue(signUp);

            stage.hide();
            loadGame(stage);
        } else {
            System.out.println("Password and password confirmation are not equal, try again.");
            password.clear();
            passwordConf.clear();
        }
    }

    private boolean checkPassword(String a, String b) {
        return a.equals(b);
    }

    private void loadGame(Stage stage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            MainController mainController = new MainController();
            loader.setLocation(Client.class.getResource("/fxml/mainView.fxml"));
            loader.setController(mainController);
            SplitPane rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
            mainController.loadGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
