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
import pdg5.common.protocol.Pass;
import pdg5.common.protocol.Play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController extends AbstractController {

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
        setDragForList(deckList, true, false);
        setDragForList(userList, false, false);
        setDragForList(userBonusList, false, true);

        initLists(userList, adversaryList, deckList, userBonusList, adversaryBonusList);

        // Last box +10
        setModifier(userList.get(userList.size()-1), "bonus", "+10");

        setModifier(userList.get(1), "mult2", "2xL");
        setModifier(userList.get(3), "mult3", "3xL");
        setModifier(userList.get(4), "adv_bonus", "W");
    }

    @SafeVarargs
    private final void initLists(List<StackPane>... lists) {
        for(List<StackPane> list : lists) {
            for (StackPane ap : list) {
                ap.getChildren().add(new Label());
            }
        }
    }

    private void setModifier(StackPane box, String style, String text) {
        box.getStyleClass().add(style);
        ((Label)box.getChildren().get(0)).setText(text);
    }

    private void setDragForList(List<StackPane> list, boolean isDeck, boolean isBonus) {
        for(StackPane ap : list) {
            ap.setOnDragDetected(this::handleOnDragDetected);
            ap.setOnDragEntered(this::handleOnDragEntered);
            if(isDeck)
                ap.setOnDragOver(this::handleOnDragOverDeck);
            else if(isBonus)
                ap.setOnDragOver(this::handleOnDragOverBonus);
            else
                ap.setOnDragOver(this::handleOnDragOver);
            ap.setOnDragDropped(this::handleOnDragDropped);
            ap.setOnDragDone(this::handleOnDragDone);
        }
    }

    private void handleOnDragDetected(MouseEvent event) {
        StackPane source = (StackPane) event.getSource();

        if(source.getChildren().size() < 2) {
            event.consume();
            return;
        }

        GTile tile = (GTile)source.getChildren().get(1);
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        db.setDragView(source.getChildren().get(1).snapshot(parameters, null));

        // Put a string on a dragboard
        ClipboardContent content = new ClipboardContent();
        content.put(tileFormat, tile.getModel());
        db.setContent(content);

        event.consume();
    }

    private void handleOnDragEntered(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        event.consume();
    }

    private void handleOnDragOver(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        if(source.getChildren().size() < 2) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void handleOnDragOverDeck(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        if(source.getChildren().size() < 2) {
            Dragboard db = event.getDragboard();
            if(db.hasContent(tileFormat) && !((Tile)db.getContent(tileFormat)).isBonus())
                event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void handleOnDragOverBonus(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        if(source.getChildren().size() < 2) {
            Dragboard db = event.getDragboard();
            if(db.hasContent(tileFormat) && ((Tile)db.getContent(tileFormat)).isBonus())
                event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void handleOnDragDropped(DragEvent event) {
        StackPane source = (StackPane) event.getSource();
        Dragboard db = event.getDragboard();

        boolean success = false;
        if (db.hasContent(tileFormat)) {
            source.getChildren().add(1, new GTile((Tile)db.getContent(tileFormat)));
            source.getStyleClass().add("covered");
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    private void handleOnDragDone(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            StackPane source = (StackPane) event.getSource();
            source.getChildren().remove(1);
            source.getStyleClass().remove("covered");
        }
        event.consume();
    }

    private void updateList(List<Tile> listFrom, int size, List<StackPane> listDest) {
        for (int i = 0; i < size; i++) {
            if(listDest.get(i).getChildren().size() > 1)
                listDest.get(i).getChildren().remove(1);
            if (!listFrom.isEmpty() && i < listFrom.size()) {
                listDest.get(i).getChildren().add(1, new GTile(listFrom.get(i)));
            }
        }
    }

    private void cleanList(List<StackPane> list, int size) {
        for (int i = 0; i < size; i++) {
            if(list.get(i).getChildren().size() < 2) {
                continue;
            }
            list.get(i).getChildren().remove(1);
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
                    remainingTiles.setText(String.valueOf(g.getNbLeftTile()) + " tuilles restante(s)");
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
            if(st.getChildren().size() < 2) {
                continue;
            }
            composition.push(((GTile) st.getChildren().get(1)).getModel());
        }
        cleanList(adversaryBonusList, Protocol.NUMBER_OF_EXTRA_TUILES);
        cleanList(userList, Protocol.NUMBER_OF_TUILES_PER_PLAYER);
        return composition;
    }

    public int getGameID() {
        return gameID;
    }

    private void shuffleHand() {
        ArrayList<Tile> temp = new ArrayList<>();
        for (StackPane st: deckList) {
            if(st.getChildren().size() < 2) {
                continue;
            }
            temp.add(((GTile) st.getChildren().get(1)).getModel());
        }
        Collections.shuffle(temp);
        updateList(temp, Protocol.NUMBER_OF_TUILES_PER_PLAYER, deckList);
    }

    @FXML
    private void swap(ActionEvent actionEvent) {
        shuffleHand();
    }

    @FXML
    private void play(ActionEvent actionEvent) {
        ClientSender clientSender = new ClientSender();
        clientSender.add(new Play(getPlay(userList), gameID));
    }

    @FXML
    private void discard(ActionEvent actionEvent) {
        ClientSender clientSender = new ClientSender();
        clientSender.add(new Pass(gameID));
    }
}
