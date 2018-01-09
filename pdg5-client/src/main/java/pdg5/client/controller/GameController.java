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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pdg5.client.ClientSender;
import pdg5.client.view.GTile;
import pdg5.common.Protocol;
import pdg5.common.game.Composition;
import pdg5.common.game.Tile;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Play;

import java.util.ArrayList;
import java.util.Collections;
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

    private int gameID;

    @FXML
    public void initialize() {

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

    private void updateList(List<Tile> listFrom, int size, List<StackPane> listDest) {
        for (int i = 0; i < size; i++) {
            listDest.get(i).getChildren().clear();
            if (!listFrom.isEmpty() && i < listFrom.size()) {
                listDest.get(i).getChildren().add(new GTile(listFrom.get(i)));
            }
        }
    }

    private void cleanList(List<StackPane> list, int size) {
        for (int i = 0; i < size; i++) {
            if(list.get(i).getChildren().size() == 0) {
                continue;
            }
            list.get(i).getChildren().remove(0);
        }
    }

    private void updatePlayer(Game g) {
        updateList(g.getAddedTile(), Protocol.NUMBER_OF_TUILES_PER_PLAYER, deckList);
        updateList(g.getBonusLetters(), Protocol.NUMBER_OF_EXTRA_TUILES, userBonusList);
        updateList(g.getLastWordPlayed(), Protocol.NUMBER_OF_TUILES_PER_PLAYER, adversaryList);
        updateList(g.getOpponentBonusLetters(), Protocol.NUMBER_OF_EXTRA_TUILES, adversaryBonusList);
        if (g.isYourTurn()) {
            cleanList(userList, Protocol.NUMBER_OF_TUILES_PER_PLAYER);
            cleanList(adversaryBonusList, Protocol.NUMBER_OF_EXTRA_TUILES);
        } else {
            cleanList(userBonusList, Protocol.NUMBER_OF_EXTRA_TUILES);
            cleanList(adversaryList, Protocol.NUMBER_OF_TUILES_PER_PLAYER);
        }
    }

    public void updateGame(Game g) {
        gameID = g.getID();
        Platform.runLater(() -> {
                    updatePlayer(g);
                    remainingTiles.setText(String.valueOf(g.getNbLeftTile()));
                    adversaryScore.setText(String.valueOf(g.getOpponentScore()));
                    userScore.setText(String.valueOf(g.getScore()));
                    adversaryName.setText(g.getOpponentName());
                    userName.setText(g.getNamePlayer());
                }
        );
    }

    private Composition getPlay(List<StackPane> list) {
        Composition composition = new Composition();
        for (StackPane st: list) {
            if(st.getChildren().size() == 0) {
                continue;
            }
            composition.push(((GTile) st.getChildren().get(0)).getModel());
        }
        cleanList(adversaryBonusList, Protocol.NUMBER_OF_EXTRA_TUILES);
        return composition;
    }

    public int getGameID() {
        return gameID;
    }

    private void shuffleHand() {
        ArrayList<Tile> temp = new ArrayList<>();
        for (StackPane st: deckList) {
            if(st.getChildren().size() == 0) {
                continue;
            }
            temp.add(((GTile) st.getChildren().get(0)).getModel());
        }
        Collections.shuffle(temp);
        updateList(temp, Protocol.NUMBER_OF_TUILES_PER_PLAYER, deckList);
    }

    @FXML
    private void play(ActionEvent actionEvent) {
        ClientSender clientSender = new ClientSender();
        clientSender.add(new Play(getPlay(userList), gameID));
    }


}
