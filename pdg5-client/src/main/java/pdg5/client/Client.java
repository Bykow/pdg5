package pdg5.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.controller.MainController;
import pdg5.client.util.ClientRequestManager;
import pdg5.common.Protocol;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client extends Application {

    private static final String TRUSTSTORE_LOCATION = "";
    private MainController mainController;
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private AnchorPane gameLayout;
    private SSLSocket socket;
    private ClientListener listener;
    private ClientSender sender;
    private ClientRequestManager requestManager;
    private Boolean isConnected;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Lauch of client
     *
     * @param primaryStage first stage to load
     * @throws InterruptedException
     */
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WordOn Desktop");
        this.primaryStage.setResizable(false);

        // Set app icon
        this.primaryStage.getIcons().add(new Image(Client.class.getResourceAsStream("/img/icon.png")));

        initRootLayout();

        System.setProperty("javax.net.ssl.trustStore", "clientKeyStore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "pdg5Password");

        File configFile = new File(System.getProperty("user.dir") + File.separator + Protocol.CONFIG_FILE);
        Properties config = new Properties();

        // Try to load properties file
        try {
            config.load(new BufferedInputStream(new FileInputStream(configFile)));
        } catch (IOException e) {}

        // Get properties if exist or use default value
        String serverAddr = config.getProperty("SERVER_ADRR", Protocol.DEFAULT_SERVER);
        int serverPort = Integer.getInteger(config.getProperty("SERVER_PORT"), Protocol.DEFAULT_PORT);

        // Try connect
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            this.socket = (SSLSocket) factory.createSocket(serverAddr, serverPort);

            socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());

            // Init for use into controller
            sender = new ClientSender(socket);
            listener = new ClientListener(socket);
            new Thread(sender).start();
            new Thread(listener).start();
            isConnected = true;
        } catch (Exception e) {
            System.err.println("Connection error");
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("/fxml/loginView.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

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
