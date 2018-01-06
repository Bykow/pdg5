package pdg5.client.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import pdg5.client.Client;

import java.io.IOException;

/**
 * Created on 14.11.17 by Bykow
 */
public abstract class AbstractController {

    protected void loadGame(Stage stage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            MainController mainController = new MainController();
            loader.setLocation(Client.class.getResource("/fxml/mainView.fxml"));
            loader.setController(mainController);
            SplitPane rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
            mainController.loadGame();
            mainController.loadLoby();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
