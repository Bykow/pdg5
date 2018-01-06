/**
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : GameController.java
 Auteur(s)   : Andrea Cotza
 Date        : 24.10.2017
 
 But         : <‡ complÈter>
 
 Remarque(s) : <‡ complÈter>
 
 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

package pdg5.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pdg5.client.view.GTile;
import pdg5.common.game.Tile;

import java.util.List;

public class GameController {

    private static final DataFormat tileFormat = new DataFormat("tile.model");

    @FXML
    private List<StackPane> deckList;
    @FXML
    private List<StackPane> userList;
    @FXML
    private List<StackPane> userBonusList;
    @FXML
    private List<StackPane> adversaryList;
    @FXML
    private List<StackPane> adversaryBonusList;

    @FXML
    private Label remainingTiles;
    @FXML
    private Label adversaryScore;
    @FXML
    private Label userScore;
    @FXML
    private Label adversaryName;
    @FXML
    private Label userName;

    @FXML
    public void initialize() {
        deckList.get(0).getChildren().add(new GTile(new Tile('A', 2)));
        deckList.get(1).getChildren().add(new GTile(new Tile('F', 1)));

        for(StackPane ap : deckList) {
            ap.setOnDragDetected(this::handleOnDragDetected);
            ap.setOnDragEntered(this::handleOnDragEntered);
            ap.setOnDragOver(this::handleOnDragOver);
            ap.setOnDragDropped(this::handleOnDragDropped);
            ap.setOnDragDone(this::handleOnDragDone);
        }

        for(StackPane ap : userList) {
            ap.setOnDragDetected(this::handleOnDragDetected);
            ap.setOnDragEntered(this::handleOnDragEntered);
            ap.setOnDragOver(this::handleOnDragOver);
            ap.setOnDragDropped(this::handleOnDragDropped);
            ap.setOnDragDone(this::handleOnDragDone);
        }

        for(StackPane ap : userBonusList) {
            ap.setOnDragDetected(this::handleOnDragDetected);
            ap.setOnDragEntered(this::handleOnDragEntered);
            ap.setOnDragOver(this::handleOnDragOver);
            ap.setOnDragDropped(this::handleOnDragDropped);
            ap.setOnDragDone(this::handleOnDragDone);
        }
    }

    private void handleOnDragDetected(MouseEvent event) {
        StackPane source = (StackPane) event.getSource();

        if(source.getChildren().size() == 0) {
            event.consume();
            return;
        }

        GTile tile = (GTile)source.getChildren().get(0);
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        db.setDragView(source.getChildren().get(0).snapshot(parameters, null));

        // Put a string on a dragboard
        ClipboardContent content = new ClipboardContent();
        content.put(tileFormat, tile.getModel());
        db.setContent(content);

        event.consume();
    }

    private void handleOnDragEntered(DragEvent event) {
        StackPane source = (StackPane) event.getSource();
        if(source.getChildren().size() == 0) {
            //source.setStyle("-fx-border-color: black;");
        }

        event.consume();
    }

    private void handleOnDragOver(DragEvent event) {
        StackPane source = (StackPane) event.getSource();
        if(source.getChildren().size() == 0)
            event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    private void handleOnDragDropped(DragEvent event) {
        StackPane source = (StackPane) event.getSource();
        Dragboard db = event.getDragboard();

        boolean success = false;
        if (db.hasContent(tileFormat)) {
            source.getChildren().setAll(new GTile((Tile)db.getContent(tileFormat)));
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    private void handleOnDragDone(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            StackPane source = (StackPane) event.getSource();
            source.getChildren().remove(0);
        }
        event.consume();
    }

    @FXML
    private void play(ActionEvent actionEvent) {
        System.out.println("play");
    }

}
