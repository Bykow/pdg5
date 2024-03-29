/**
 * -----------------------------------------------------------------------------------
 * Laboratoire : <nn>
 * Fichier     : Tile.java
 * Auteur(s)   : Andrea Cotza
 * Date        : 24.10.2017
 * <p>
 * But         : <‡ complÈter>
 * <p>
 * Remarque(s) : <‡ complÈter>
 * <p>
 * Compilateur : jdk1.8.0_60
 * -----------------------------------------------------------------------------------
 */

package pdg5.client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pdg5.client.Client;
import pdg5.common.game.Tile;

import java.io.IOException;

/**
 * Graphical representation of a Tile
 */
public class GTile extends Pane {
    private static FXMLLoader loader = initLoader();

    private Tile model;

    @FXML
    private Label letter;
    @FXML
    private Label point;

    /**
     * Ctor
     *
     * @param model tile to generate
     */
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

    /**
     * FXMLLoader
     *
     * @return Loader
     */
    private static FXMLLoader initLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("/fxml/tile.fxml"));
        return loader;
    }

    /**
     * Initialize the GTile
     */
    @FXML
    public void initialize() {
        if (model.isBonus())
            this.getStyleClass().add("bonus");
        letter.setText(String.valueOf(model.getLetter()));
        point.setText(String.valueOf(model.getValue()));
    }

    /**
     * Default getter for Tile
     *
     * @return Tile
     */
    public Tile getModel() {
        return model;
    }
}
