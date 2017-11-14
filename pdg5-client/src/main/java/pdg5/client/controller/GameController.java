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

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import pdg5.common.game.Tile;

import java.util.List;

public class GameController {

    private static final DataFormat tileFormat = new DataFormat("tile.model");

    @FXML
    private List<AnchorPane> deckList;

    @FXML
    public void initialize() {
        deckList.get(0).getChildren().addAll(new pdg5.client.view.Tile(new Tile('A', 2)));
        deckList.get(1).getChildren().addAll(new pdg5.client.view.Tile(new Tile('F', 1)));

        for(AnchorPane ap : deckList) {
            ap.setOnDragDetected(this::handleOnDragDetected);
            ap.setOnDragEntered(this::handleOnDragEntered);
            ap.setOnDragOver(this::handleOnDragOver);
            ap.setOnDragDropped(this::handleOnDragDropped);
            ap.setOnDragDone(this::handleOnDragDone);
        }
    }

    private void handleOnDragDetected(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();

        if(source.getChildren().size() == 0) {
            event.consume();
            return;
        }

        pdg5.client.view.Tile tile = (pdg5.client.view.Tile)source.getChildren().get(0);
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
        AnchorPane source = (AnchorPane) event.getSource();
        if(source.getChildren().size() == 0) {
            //source.setStyle("-fx-border-color: black;");
        }

        event.consume();
    }

    private void handleOnDragOver(DragEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        if(source.getChildren().size() == 0)
            event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    private void handleOnDragDropped(DragEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        Dragboard db = event.getDragboard();

        boolean success = false;
        if (db.hasContent(tileFormat)) {
            source.getChildren().setAll(new pdg5.client.view.Tile((Tile)db.getContent(tileFormat)));
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    private void handleOnDragDone(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            AnchorPane source = (AnchorPane) event.getSource();
            source.getChildren().remove(0);
        }
        event.consume();
    }

}
