/**
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : LoginController.java
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

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink btnSignup;

    private Scene registerScene;

    @FXML
    public void initialize() {
    }

    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        Stage stage = (Stage)btnLogin.getScene().getWindow();
        stage.hide();
        loadGame(stage);
    }

    @FXML
    private void handleSwitch(ActionEvent actionEvent) {
        Stage stage = (Stage)btnLogin.getScene().getWindow();

        if(registerScene == null) {
            Scene currentScene = btnLogin.getScene();

            try {
                FXMLLoader loader = new FXMLLoader();
                RegisterController controller = new RegisterController(currentScene);
                loader.setLocation(LoginController.class.getResource("/fxml/registerView.fxml"));
                loader.setController(controller);
                AnchorPane rootLayout = loader.load();

                registerScene = new Scene(rootLayout);

                stage.setScene(registerScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(registerScene);
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
