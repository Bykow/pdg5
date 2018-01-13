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

/**
 * Main controller
 */
public class MainController extends AbstractController {

    // Listener for Server messages
    private ClientListener listener;
    // Sender to send messages to server
    private ClientSender sender;
    // Client logic to process recieved messages
    private ClientRequestManager requestManager;

    // Links to subcontrollers
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

    /**
     * Ctor
     */
    public MainController() {
        listener = new ClientListener();
        sender = new ClientSender();

        this.requestManager = new ClientRequestManager(this);
    }

    /**
     * Starts the logic of the client
     */
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

    /**
     * Load the Game Controller
     */
    public void loadGame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            gameController = new GameController(sender, this);
            loader.setLocation(MainController.class.getResource("/fxml/gameView.fxml"));
            loader.setController(gameController);
            layout = loader.load();
            gameContainer.getChildren().add(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the Loby Controller
     */
    public void loadLoby() {
        try {
            FXMLLoader loader = new FXMLLoader();
            lobyController = new LobyController(sender, this);
            loader.setLocation(MainController.class.getResource("/fxml/lobyView.fxml"));
            loader.setController(lobyController);
            layout = loader.load();
            lobyContainer.getChildren().add(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the Chat controller
     */
    public void loadChat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            chatController = new ChatController(sender);
            loader.setLocation(MainController.class.getResource("/fxml/chatView.fxml"));
            loader.setController(chatController);
            layout = loader.load();
            Platform.runLater(()->chatContainer.getChildren().add(layout));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allow you to send message to the server
     *
     * @param m message to send
     */
    public void sendMessage(Message m) {
        sender.add(m);
    }

    /**
     * Displays a toast message
     *
     * @param m message to display
     */
    public void displayToast(String m) {
        Stage stage = (Stage) gameContainer.getScene().getWindow();
        Platform.runLater(() -> new Toast(stage, m).show());
    }

    /**
     * Getter for GameController, makes sure it is not null, creates it if needed
     *
     * @return Game Controller
     */
    public GameController getGameController() {
        if (!gameContainer.getChildren().get(1).isVisible()) {
            gameContainer.getChildren().get(1).setVisible(true);
        }
        return gameController;
    }

    /**
     * Getter for LobyController
     *
     * @return LobyController
     */
    public LobyController getLobyController() {
        return lobyController;
    }

    /**
     * Getter for ChatController, makes sure it is not null, creates it if needed
     *
     * @return ChatController
     */
    public ChatController getChatController() {
        if (!chatContainer.getChildren().get(1).isVisible()) {
            chatContainer.getChildren().get(1).setVisible(true);
        }
        return chatController;
    }

    /**
     * Behaviour for a logout
     */
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

        // Switches the scene
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Makes a String have its first letter uppercase, mainly used to display username correctly
     *
     * @param s string to process
     * @return string with uppercase
     */
    public String upperCaseFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
