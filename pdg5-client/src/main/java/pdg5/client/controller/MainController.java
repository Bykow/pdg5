/**
 * -----------------------------------------------------------------------------------
 * Laboratoire : <nn>
 * Fichier : MainController.java Auteur(s) : Andrea Cotza Date : 03.10.2017
 * <p>
 * But : <à compléter>
 * <p>
 * Remarque(s) : <à compléter>
 * <p>
 * Compilateur : jdk1.8.0_60
 * -----------------------------------------------------------------------------------
 */
package pdg5.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.ClientListener;
import pdg5.client.ClientSender;
import pdg5.client.util.ClientRequestManager;
import pdg5.client.util.Toast;
import pdg5.common.protocol.Message;

import java.io.IOException;

public class MainController {

    private ClientListener listener;
    private ClientSender sender;
    private ClientRequestManager requestManager;

    private GameController gameController;

    @FXML
    private AnchorPane gameContainer;
    @FXML
    private AnchorPane lobyContainer;

    private AnchorPane layout;

    @FXML
    public void initialize() {
    }

    public void loadGame() {
        // Already start
        listener = new ClientListener();
        // Already start
        sender = new ClientSender();

        this.requestManager = new ClientRequestManager(this);

        try {
            FXMLLoader loader = new FXMLLoader();
            gameController = new GameController();
            loader.setLocation(MainController.class.getResource("/fxml/gameView.fxml"));
            loader.setController(gameController);
            layout = loader.load();
            gameContainer.getChildren().setAll(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Process message
        new Thread(() -> {
            while (true) {
                sender.add(
                        requestManager.execute(
                                listener.take()
                        )
                );
            }
        }).start();

    }

    public void loadLoby() {
        try {
            FXMLLoader loader = new FXMLLoader();
            LobyController controller = new LobyController(sender);
            loader.setLocation(MainController.class.getResource("/fxml/lobyView.fxml"));
            loader.setController(controller);
            layout = loader.load();
            lobyContainer.getChildren().setAll(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allow you to send message to the server
     *
     * @param m
     */
    public void sendMessage(Message m) {
        sender.add(m);
    }

    public void displayToast(String m) {
        Stage stage = (Stage) gameContainer.getScene().getWindow();
        new Toast(stage, m).show();
    }

    public GameController getGameController() {
        return gameController;
    }
}
