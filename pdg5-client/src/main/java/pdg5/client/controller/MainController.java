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
import pdg5.client.ClientListener;
import pdg5.client.ClientSender;
import pdg5.client.util.ClientRequestManager;

import java.io.IOException;

public class MainController {

    private ClientListener listener;
    private ClientSender sender;
    private ClientRequestManager requestManager;

    @FXML
    private AnchorPane gameContainer;

    private AnchorPane layout;

    @FXML
    public void initialize() {}

    public void loadGame() {
        listener = new ClientListener();
        sender = new ClientSender();
        this.requestManager = new ClientRequestManager();

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

        // Start listen
        new Thread(listener).start();

        // Start send
        new Thread(sender).start();

        // Process message
        sender.add(
                requestManager.execute(
                        listener.take()
                )
        );

    }
}
