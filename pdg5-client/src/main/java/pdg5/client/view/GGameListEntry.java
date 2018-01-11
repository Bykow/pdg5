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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pdg5.client.Client;
import pdg5.common.game.GameModel;
import pdg5.common.game.Tile;
import pdg5.common.protocol.Game;

import java.io.IOException;
import java.util.Date;

public class GGameListEntry extends AnchorPane {
    private static FXMLLoader loader = initLoader();

    @FXML
    private Label username;
    @FXML
    private Label msg;
    @FXML
    private Label score;
    @FXML
    private Button btnDelete;

    private Game model;
    private EventHandler<? super MouseEvent> mouseClickHandler;
    private EventHandler<? super ActionEvent> deleteHandler;

    private static FXMLLoader initLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("/fxml/gameListEntry.fxml"));
        return loader;
    }

    public GGameListEntry(Game model, EventHandler<? super MouseEvent> mouseClickHandler, EventHandler<? super ActionEvent> deleteHandler) {
        super();

        this.model = model;
        this.mouseClickHandler = mouseClickHandler;
        this.deleteHandler = deleteHandler;

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
        this.setOnMouseClicked(mouseClickHandler);
        if (model.getState() == GameModel.State.FINISHED)
            btnDelete.setDisable(false);
        else {
            String time;
            Date elapsed = new Date(new Date().getTime() - model.getLastActivity().getTime());
            if (elapsed.getTime() > 60 * 60 * 1000)
                time = ((int) elapsed.getTime() / (60 * 60 * 1000)) + " h";
            else
                time = ((int) elapsed.getTime() / (60 * 1000)) + " min";

            score.setText(time);
        }

        username.setText(model.getOpponentName().substring(0, 1).toUpperCase() + model.getOpponentName().substring(1).toLowerCase());
        switch (model.getState()) {
            case IN_PROGRESS:
                if (model.isYourTurn()) {
                    msg.setText("a joué " + Tile.tilesToString(model.getLastWordPlayed()));
                } else {
                    msg.setText("vous avez joué " + Tile.tilesToString(model.getLastWordPlayed()));
                }
                break;
            case END_MODE:
                break;
            case FINISHED:
                msg.setText("La partie est terminée");
                break;
            case OUTDATED:
                msg.setText("La partie est expirée");
                break;
        }
    }

    public void setSelected(boolean value) {
        if (value)
            this.getStyleClass().add("selected");
        else
            this.getStyleClass().clear();
    }

    public Game getModel() {
        return model;
    }

    public void delete(ActionEvent actionEvent) {
        deleteHandler.handle(actionEvent);
    }

}