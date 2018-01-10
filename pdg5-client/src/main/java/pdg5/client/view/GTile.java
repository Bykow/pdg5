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
import javafx.scene.layout.Pane;
import pdg5.client.Client;
import pdg5.common.game.Tile;

import java.io.IOException;

public class GTile extends Pane {
    private static FXMLLoader loader = initLoader();

    private Tile model;

    @FXML
    private Label letter;
    @FXML
    private Label point;

    private static FXMLLoader initLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("/fxml/tile.fxml"));
        return loader;
    }

    public GTile(Tile model) {
        super();

        this.model = model;

        // Load root layout from fxml file.
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        if(model.isBonus())
            this.getStyleClass().add("bonus");
        letter.setText(String.valueOf(model.getLetter()));
        point.setText(String.valueOf(model.getValue()));
    }

    public Tile getModel() {
        return model;
    }
}
