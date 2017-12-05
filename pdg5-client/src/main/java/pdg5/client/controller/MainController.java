/**
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : MainController.java
 Auteur(s)   : Andrea Cotza
 Date        : 03.10.2017
 
 But         : <‡ complÈter>
 
 Remarque(s) : <‡ complÈter>
 
 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/
package pdg5.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane gameContainer;
    @FXML
    private AnchorPane chatContainer;

    private AnchorPane layout;

    @FXML
    public void initialize() {}

    public void loadGame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            GameController controller = new GameController();
            loader.setLocation(MainController.class.getResource("/fxml/gameView.fxml"));
            loader.setController(controller);
            layout = loader.load();
            gameContainer.getChildren().setAll(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadChat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            ChatController controller = new ChatController();
            loader.setLocation(MainController.class.getResource("/fxml/chatView.fxml"));
            loader.setController(controller);
            layout = loader.load();
            chatContainer.getChildren().setAll(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
