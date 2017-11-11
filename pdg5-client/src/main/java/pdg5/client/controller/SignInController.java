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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.Client;
import pdg5.client.ClientSender;
import pdg5.common.protocol.SignIn;

import java.io.IOException;

public class SignInController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink btnSignup;

    private Scene signUpScene;

    @FXML
    public void initialize() {
    }

    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        Stage stage = (Stage)btnLogin.getScene().getWindow();

        ClientSender cs = new ClientSender();
        SignIn signIn = new SignIn(username.getText(), password.getText());

        cs.add(signIn);

        stage.hide();
        loadGame(stage);
    }

    @FXML
    private void handleSwitch(ActionEvent actionEvent) {
        Stage stage = (Stage)btnLogin.getScene().getWindow();

        if(signUpScene == null) {
            Scene currentScene = btnLogin.getScene();

            try {
                FXMLLoader loader = new FXMLLoader();
                SignUpController controller = new SignUpController(currentScene);
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
