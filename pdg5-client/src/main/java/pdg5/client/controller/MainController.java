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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.Client;
import pdg5.client.ClientListener;
import pdg5.client.ClientSender;
import pdg5.client.util.ClientRequestManager;
import pdg5.client.util.Toast;
import pdg5.common.protocol.Logout;
import pdg5.common.protocol.Message;

import java.io.IOException;

public class MainController extends AbstractController {

    private ClientListener listener;
    private ClientSender sender;
    private ClientRequestManager requestManager;

    private GameController gameController;
    private LobyController lobyController;
    private ChatController chatController;

    @FXML
    private AnchorPane gameContainer;
    @FXML
    private AnchorPane lobyContainer;
    @FXML
    private AnchorPane chatContainer;

    private AnchorPane layout;

    @FXML
    public void initialize() {
    }

    public MainController() {
        listener = new ClientListener();
        sender = new ClientSender();

        this.requestManager = new ClientRequestManager(this);
    }

    public void startLogic() {
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

    public void loadGame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            gameController = new GameController(sender, this);
            loader.setLocation(MainController.class.getResource("/fxml/gameView.fxml"));
            loader.setController(gameController);
            layout = loader.load();
            gameContainer.getChildren().setAll(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLoby() {
        try {
            FXMLLoader loader = new FXMLLoader();
            lobyController = new LobyController(sender, this);
            loader.setLocation(MainController.class.getResource("/fxml/lobyView.fxml"));
            loader.setController(lobyController);
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
        Platform.runLater(() -> new Toast(stage, m).show());
    }

    public void loadChat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            chatController = new ChatController(sender);
            loader.setLocation(MainController.class.getResource("/fxml/chatView.fxml"));
            loader.setController(chatController);
            layout = loader.load();
            chatContainer.getChildren().setAll(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    public LobyController getLobyController() {
        return lobyController;
    }

    public ChatController getChatController() {
        return chatController;
    }

    public void logout() {
        sender.add(new Logout());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("/fxml/loginView.fxml"));
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show the scene containing the root layout.
        Scene scene = new Scene(layout);

        Stage stage = (Stage)gameContainer.getScene().getWindow();

        stage.hide();

        stage.setScene(scene);
        stage.show();
    }
}
