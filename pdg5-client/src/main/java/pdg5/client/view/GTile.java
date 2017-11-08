package pdg5.client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pdg5.client.Client;
import pdg5.common.game.Tuile;

import java.io.IOException;

public class GTile extends Pane {
    private static FXMLLoader loader = initLoader();

    private Tuile model;

    @FXML
    private Label letter;
    @FXML
    private Label point;

    private static FXMLLoader initLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("/fxml/tile.fxml"));
        return loader;
    }

    public GTile(Tuile model) {
        super();

        this.model = model;

        try {
            // Load root layout from fxml file.
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
            loader.getBuilderFactory().getBuilder(Pane.class).build();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        letter.setText(String.valueOf(model.getLetter()));
        point.setText(String.valueOf(model.getValue()));
    }

    public Tuile getModel() {
        return model;
    }
}
