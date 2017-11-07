package pdg5.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.controller.MainController;
import pdg5.common.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Application {

    private MainController mainController;
    private Stage primaryStage;
    //private SplitPane rootLayout;
    private AnchorPane rootLayout;
    private AnchorPane gameLayout;
    private Socket socket;
    private ClientListener listener;
    private ClientSender sender;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WordOn Desktop");

        initRootLayout();

        // Try connect
        try {
            this.socket = new Socket(Protocol.DEFAULT_SERVER, Protocol.DEFAULT_PORT);
            this.sender = new ClientSender(socket);
            this.listener = new ClientListener(socket);

        } catch (IOException e) {
            System.err.println("Connection error");
        }

        // Start listen
        new Thread(listener).start();

        // Start send
        new Thread(sender).start();

        // Process message
        // TODO Process message
        System.out.println("Client.start ELSE");
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            /*FXMLLoader loader = new FXMLLoader();
            mainController = new MainController();
            loader.setLocation(Client.class.getResource("/fxml/mainView.fxml"));
            loader.setController(mainController);
            rootLayout = loader.load();*/

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("/fxml/loginView.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();
            //mainController.loadGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
