package pdg5.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pdg5.client.controller.MainController;

import java.io.IOException;

public class Client extends Application {

    private MainController mainController;
    private Stage primaryStage;
    //private SplitPane rootLayout;
    private AnchorPane rootLayout;
    private AnchorPane gameLayout;

    public static void main(String[] args) {

        ClientNetworkManager netManager = new ClientNetworkManager();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WordOn Desktop");

        initRootLayout();
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
}
