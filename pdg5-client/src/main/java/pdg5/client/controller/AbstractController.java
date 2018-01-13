package pdg5.client.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import pdg5.client.Client;

import java.io.IOException;

/**
 * Abstract controller, first load of the GUI interface
 */
public abstract class AbstractController {

    /**
     * Load the program for the GUI.
     *
     * @param stage
     */
    protected void loadProg(Stage stage) {
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

            // Load the differents components loby game and chat
            mainController.loadLoby();
            mainController.loadGame();
            mainController.loadChat();

            // Start the sending and listening queue
            mainController.startLogic();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
