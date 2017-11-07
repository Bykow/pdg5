package pdg5.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.controller.MainController;
import pdg5.common.MessageQueue;
import pdg5.common.Protocol;
import pdg5.common.protocol.Message;

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
    private MessageQueue queueIn;
    private MessageQueue queueOut;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean launch;
    private Thread send;
    private Thread receive;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WordOn Desktop");
        this.queueIn = new MessageQueue();
        this.queueOut = new MessageQueue();
        this.launch = true;

        initRootLayout();

        // Try connect
        try {
            this.socket = new Socket(Protocol.DEFAULT_SERVER, Protocol.DEFAULT_PORT);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start listen
        receive();

        // Start send
        send();

        // Process message
        // TODO Process message
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


    private void send() {
        send = new Thread(() -> {
            while (launch) {
                try {
                    out.writeObject(queueOut.take());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        send.start();
    }

    private void receive() {
        receive = new Thread(() -> {
            try {
                while (launch) {
                    queueIn.add((Message) in.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        receive.start();
    }

    @Override
    public void stop() throws Exception {
        // TODO Maxime : Solve close problem
        receive.interrupt();
        send.interrupt();
        super.stop();
    }
}
