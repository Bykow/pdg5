package pdg5.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pdg5.client.ClientSender;
import pdg5.client.view.GTile;
import pdg5.common.game.Composition;
import pdg5.common.game.GameModel;
import pdg5.common.game.Tile;
import pdg5.common.protocol.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pdg5.common.game.Result;

public class GameController extends AbstractController {

    private static final DataFormat tileFormat = new DataFormat("tile.model");
    private final String LEFTTILESINGLE = " tuille restante";
    private final String LEFTTILEPLURAL = " tuilles restantes";
    private final String ENDWIN = "Bravo ! Tu as gagné la partie !";
    private final String ENDLOSE = "Tu as perdu la partie... ";
    private final String ENDEQUALITY = "La partie est une équalité !";

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
    private Button btnPlay;
    @FXML
    private Button btnDiscard;

    private int gameID;

    MainController mainController;
    ClientSender sender;

    public GameController(ClientSender sender, MainController mainController) {
        this.sender = sender;
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        setDragForList(deckList, true, false);
        setDragForList(userList, false, false);
        setDragForList(userBonusList, false, true);

        initLists(userList, adversaryList, deckList, userBonusList, adversaryBonusList);

        // Last box +10
        setModifier(userList.get(userList.size()-1), "BONUS", "+10");
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

    private void removeModifier(StackPane box) {
        box.getStyleClass().clear();
        ((Label)box.getChildren().get(0)).setText("");
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

    private void updateList(List<Tile> listFrom, List<StackPane> listDest) {
        for (int i = 0; i < listDest.size(); i++) {
            if(listDest.get(i).getChildren().size() > 1)
                listDest.get(i).getChildren().remove(1);
            if (!listFrom.isEmpty() && i < listFrom.size()) {
                listDest.get(i).getChildren().add(1, new GTile(listFrom.get(i)));
            }
        }
    }

    private void updateOpponentComposition(boolean isYourTurn, List<Tile> listFrom, List<StackPane> listDest, List<Composition.Square> square) {
        for (int i = 0; i < listDest.size(); i++) {
            if (isYourTurn) {
                if (listDest.get(i).getChildren().size() > 1) {
                    listDest.get(i).getChildren().remove(1);
                    listDest.get(i).getStyleClass().remove("covered");
                }
                if (!listFrom.isEmpty() && i < listFrom.size()) {
                    listDest.get(i).getChildren().add(1, new GTile(listFrom.get(i)));
                    if(square.get(i) != Composition.Square.NORMAL) {
                        listDest.get(i).getStyleClass().add("covered");
                    }
                }
            } else {
                cleanList(listDest);
            }
            if (square.get(i) != Composition.Square.NORMAL) {
                setModifier(listDest.get(i), square.get(i).name(), square.get(i).getText());
            }
        }
    }

    private void updateComposition(List<StackPane> listDest, List<Composition.Square> square) {
        for (int i = 0; i < listDest.size(); i++) {
            if (square.get(i) != Composition.Square.NORMAL) {
                setModifier(listDest.get(i), square.get(i).name(), square.get(i).getText());
            } else {
                removeModifier(listDest.get(i));
            }
        }
    }

    private void cleanList(List<StackPane> list) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getChildren().size() < 2) {
                continue;
            }
            list.get(i).getChildren().remove(1);
            list.get(i).getStyleClass().remove("covered");
        }
    }

    private void updatePlayer(Game g) {
        updateComposition(userList, g.getSquare());
        updateList(g.getAddedTile(), deckList);
        updateList(g.getBonusLetters(), userBonusList);
        updateOpponentComposition(g.isYourTurn(), g.getLastWordPlayed(), adversaryList, g.getOpponentSquare());
        updateList(g.getOpponentBonusLetters(), adversaryBonusList);
        if (g.isYourTurn()) {
            cleanList(userList);
            cleanList(adversaryBonusList);
        } else {
            cleanList(userBonusList);
        }
    }

    public void updateGame(Game g) {
        gameID = g.getID();
        if(!g.isYourTurn()) {
            btnPlay.setDisable(true);
            btnDiscard.setDisable(true);
        } else {
            btnPlay.setDisable(false);
            btnDiscard.setDisable(false);
        }
        Platform.runLater(() -> {
                    updatePlayer(g);
                    if (g.getNbLeftTile() > 1) {
                        remainingTiles.setText(String.valueOf(g.getNbLeftTile()) + LEFTTILEPLURAL);
                    } else {
                        remainingTiles.setText(String.valueOf(g.getNbLeftTile()) + LEFTTILESINGLE);
                    }
                    adversaryScore.setText(String.valueOf(g.getOpponentScore()));
                    userScore.setText(String.valueOf(g.getScore()));
                    adversaryName.setText(mainController.upperCaseFirstLetter(g.getOpponentName()));
                    userName.setText(mainController.upperCaseFirstLetter(g.getNamePlayer()));
                }
        );
    }

    private Composition getPlay(List<StackPane> list) {
        Composition composition = new Composition();
        for (StackPane st: list) {
            if(st.getChildren().size() >= 2) {
                st.getStyleClass().remove("covered");
                composition.push(((GTile) st.getChildren().get(1)).getModel());
            }
        }
        cleanList(adversaryBonusList);
        cleanList(userList);
        updateGame(mainController.getLobyController().getGameFromId(gameID));
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
        updateList(temp, deckList);
    }

    @FXML
    private void swap(ActionEvent actionEvent) {
        shuffleHand();
    }

    @FXML
    private void play(ActionEvent actionEvent) {
        sender.add(new Play(getPlay(userList), gameID));
    }

    @FXML
    private void discard(ActionEvent actionEvent) {
        sender.add(new Pass(gameID));
        cleanList(adversaryList);
    }

    public void displayEnd(End end) {
        Platform.runLater(() -> {
                    switch (end.getResult()) {
                        case WIN:
                            remainingTiles.setText(String.valueOf(ENDWIN));
                        case LOSE:
                            remainingTiles.setText(String.valueOf(ENDLOSE));
                            break;
                        case EQUALITY:
                            remainingTiles.setText(String.valueOf(ENDEQUALITY));
                            break;
                    }
                }
        );
    }

    public void displayEndState(Result result) {
        Platform.runLater(() -> {
                    switch (result) {
                        case WIN:
                            remainingTiles.setText(String.valueOf(ENDWIN));
                        case LOSE:
                            remainingTiles.setText(String.valueOf(ENDLOSE));
                            break;
                        case EQUALITY:
                            remainingTiles.setText(String.valueOf(ENDEQUALITY));
                            break;
                    }
                }
        );
    }
}
