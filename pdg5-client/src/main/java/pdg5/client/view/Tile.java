/**
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : Tile.java
 Auteur(s)   : Andrea Cotza
 Date        : 24.10.2017
 
 But         : <‡ complÈter>
 
 Remarque(s) : <‡ complÈter>
 
 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

package pdg5.client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pdg5.client.Client;

import java.io.IOException;

public class Tile extends AnchorPane {
    private pdg5.common.game.Tile model;

    @FXML
    private Label letter;
    @FXML
    private Label point;

    public Tile(pdg5.common.game.Tile model) {
        super();

        this.model = model;

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("/fxml/tile.fxml"));
            loader.setController(this);

            Pane root = loader.load();
            root.setLayoutX(10);
            root.setLayoutY(10);
            this.getChildren().addAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        letter.setText(String.valueOf(model.getLetter()));
        point.setText(String.valueOf(model.getValue()));
    }

    public pdg5.common.game.Tile getModel() {
        return model;
    }
}
