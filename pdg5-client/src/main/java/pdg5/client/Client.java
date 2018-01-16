package pdg5.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pdg5.client.controller.MainController;
import pdg5.client.util.ClientRequestManager;
import pdg5.common.Protocol;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
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

        Font.loadFont(Client.class.getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 20);
        Font.loadFont(Client.class.getResource("/font/OpenSans-Regular.ttf").toExternalForm(), 20);

        initRootLayout();

        // To use if jar will be packaged into a mac OS .app
        //File configFile = new File(new File(System.getProperty("user.dir")).getParentFile().getParent() + File.separator + Protocol.CONFIG_FILE);

        // Get properties file inside working dir
        File configFile = new File(System.getProperty("user.dir") + File.separator + Protocol.CONFIG_FILE);
        Properties config = new Properties();

        // Try to load properties file
        try {
            config.load(new BufferedInputStream(new FileInputStream(configFile)));
        } catch (IOException e) {
        }

        // Get properties if exist or use default value
        String serverAddr = config.getProperty("SERVER_ADDR", Protocol.DEFAULT_SERVER);
        int serverPort = Integer.parseInt(config.getProperty("SERVER_PORT", String.valueOf(Protocol.DEFAULT_PORT)));

        // Try connect
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream keystoreStream = Client.class.getClassLoader().getResourceAsStream("ClientKeyStore.jks");
            keystore.load(keystoreStream, "pdg5Password".toCharArray());
            trustManagerFactory.init(keystore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, trustManagers, null);

            SSLSocketFactory factory = ctx.getSocketFactory();
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
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText("Impossible de se connecter au serveur");
            alert.setContentText("Veuillez vérifier que le serveur soit lancé ainsi que la configuration du client 'client.properties'");
            alert.showAndWait();
            System.exit(0);
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
