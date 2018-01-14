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
import pdg5.common.game.Result;
import pdg5.common.game.Tile;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Pass;
import pdg5.common.protocol.Play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for the game model
 */
public class GameController extends AbstractController {

    private static final DataFormat tileFormat = new DataFormat("tile.model");
    // Constants string to display
    private final String LEFTTILESINGLE = " tuile restante";
    private final String LEFTTILEPLURAL = " tuiles restantes";
    private final String ENDWIN = "Bravo ! Tu as gagné !";
    private final String ENDLOSE = "Tu as perdu la partie... ";
    private final String ENDEQUALITY = "Equalité !";

    // Tiles in the hands of the user
    @FXML
    private List<StackPane> deckList;

    // Tiles in the play of the user
    @FXML
    private List<StackPane> userList;

    // Tiles bonus of the user
    @FXML
    private List<StackPane> userBonusList;

    // Tiles in the play of the opponent
    @FXML
    private List<StackPane> adversaryList;

    // Tiles bonus of the opponent
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
    @FXML
    private Button btnSwap;

    // ID of the current game displayed
    private int gameID;

    // Link to mainController
    private MainController mainController;
    // Sender used to send messages to server
    private ClientSender sender;

    /**
     * Ctor
     *
     * @param sender         sender used to send message to server
     * @param mainController mainController link
     */
    public GameController(ClientSender sender, MainController mainController) {
        this.sender = sender;
        this.mainController = mainController;
    }

    /**
     * Initializes the view with an empty board
     */
    @FXML
    public void initialize() {
        // Sets the dragging behaviour for tiles
        // User should not be able to drag bonus tile to his hand, etc
        setDragForList(deckList, true, false);
        setDragForList(userList, false, false);
        setDragForList(userBonusList, false, true);

        // Creates the lists to be used
        initLists(userList, adversaryList, deckList, userBonusList, adversaryBonusList);

        // Last box +10
        setModifier(userList.get(userList.size() - 1), "BONUS", "+10");
    }

    /**
     * Initialize the list for tiles
     *
     * @param lists lists of tiles to initialize
     */
    @SafeVarargs
    private final void initLists(List<StackPane>... lists) {
        for (List<StackPane> list : lists) {
            for (StackPane ap : list) {
                ap.getChildren().add(new Label());
            }
        }
    }

    /**
     * Behaviour on clic of swap button
     *
     * @param actionEvent
     */
    @FXML
    private void swap(ActionEvent actionEvent) {
        shuffleHand();
    }

    /**
     * Behaviour on clic of play button
     *
     * @param actionEvent
     */
    @FXML
    private void play(ActionEvent actionEvent) {
        sender.add(new Play(getPlay(), gameID));
    }

    /**
     * Behaviour on clic of discard button
     *
     * @param actionEvent
     */
    @FXML
    private void discard(ActionEvent actionEvent) {
        // Client will receive a new game after it sends this message to server. All the logic is server side.
        sender.add(new Pass(gameID));
        cleanList(adversaryList);
        cleanList(userList);
    }

    /**
     * Sets a modifier for a tile slot, means the slot is worth 3x point or something else
     *
     * @param box   The StackPane to modify
     * @param style The bonus to add to the StackPane
     * @param text  The text that is displayed
     */
    private void setModifier(StackPane box, String style, String text) {
        box.getStyleClass().add(style);
        ((Label) box.getChildren().get(0)).setText(text);
    }

    /**
     * Removes a modifier from a StackPane, making it a default slot
     *
     * @param box StackPane to clear
     */
    private void removeModifier(StackPane box) {
        box.getStyleClass().clear();
        ((Label) box.getChildren().get(0)).setText("");
    }

    /**
     * Sets the dragging behaviour for a given list
     *
     * @param list    list to set
     * @param isDeck  boolean, changes the behaviour in some situations
     * @param isBonus boolean, changes the behaviour in some situations
     */
    private void setDragForList(List<StackPane> list, boolean isDeck, boolean isBonus) {
        for (StackPane ap : list) {
            ap.setOnDragDetected(this::handleOnDragDetected);
            ap.setOnDragEntered(this::handleOnDragEntered);
            if (isDeck)
                ap.setOnDragOver(this::handleOnDragOverDeck);
            else if (isBonus)
                ap.setOnDragOver(this::handleOnDragOverBonus);
            else
                ap.setOnDragOver(this::handleOnDragOver);
            ap.setOnDragDropped(this::handleOnDragDropped);
            ap.setOnDragDone(this::handleOnDragDone);
        }
    }

    /**
     * Behaviour for GUI when dragging starts
     *
     * @param event
     */
    private void handleOnDragDetected(MouseEvent event) {
        StackPane source = (StackPane) event.getSource();

        if (source.getChildren().size() < 2) {
            event.consume();
            return;
        }

        GTile tile = (GTile) source.getChildren().get(1);
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

    /**
     * JavaFx dragging behaviour
     *
     * @param event
     */
    private void handleOnDragEntered(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        event.consume();
    }

    /**
     * JavaFx dragging over
     *
     * @param event
     */
    private void handleOnDragOver(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        if (source.getChildren().size() < 2) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    /**
     * JavaFx dragging over the deck
     *
     * @param event
     */
    private void handleOnDragOverDeck(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        if (source.getChildren().size() < 2) {
            Dragboard db = event.getDragboard();
            if (db.hasContent(tileFormat) && !((Tile) db.getContent(tileFormat)).isBonus())
                event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    /**
     * JavaFx draggin of a bonus
     *
     * @param event
     */
    private void handleOnDragOverBonus(DragEvent event) {
        StackPane source = (StackPane) event.getSource();

        if (source.getChildren().size() < 2) {
            Dragboard db = event.getDragboard();
            if (db.hasContent(tileFormat) && ((Tile) db.getContent(tileFormat)).isBonus())
                event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    /**
     * Behaviour when dragging is released
     *
     * @param event
     */
    private void handleOnDragDropped(DragEvent event) {
        StackPane source = (StackPane) event.getSource();
        Dragboard db = event.getDragboard();

        boolean success = false;
        if (db.hasContent(tileFormat)) {
            source.getChildren().add(1, new GTile((Tile) db.getContent(tileFormat)));
            source.getStyleClass().add("covered");
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    /**
     * Behaviour when dragging is complete
     *
     * @param event
     */
    private void handleOnDragDone(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            StackPane source = (StackPane) event.getSource();
            source.getChildren().remove(1);
            source.getStyleClass().remove("covered");
        }
        event.consume();
    }

    /**
     * Updates a list of StackPane with Tiles
     *
     * @param listFrom list of Tile to add in the StackPane
     * @param listDest list of StackPane
     */
    private void updateList(List<Tile> listFrom, List<StackPane> listDest) {
        for (int i = 0; i < listDest.size(); i++) {

            // Empty the StackPane if not already empty
            if (listDest.get(i).getChildren().size() > 1)
                listDest.get(i).getChildren().remove(1);

            // Fills the StackPane
            if (!listFrom.isEmpty() && i < listFrom.size()) {
                listDest.get(i).getChildren().add(1, new GTile(listFrom.get(i)));
            }
        }
    }

    /**
     * Updates the opponent list, there are a lot of different expected behaviours
     *
     * @param isYourTurn boolean true if it is the user turn, false otherwise
     * @param listFrom   list of Tile to add in the StackPanes
     * @param square     list of bonus on the StackPanes
     */
    private void updateOpponentComposition(boolean isYourTurn, List<Tile> listFrom, List<Composition.Square> square) {
        cleanList(adversaryList);

        for (int i = 0; i < adversaryList.size(); i++) {

            // Makes sure the bonus on the StackPanes are correct
            if (square.get(i) != Composition.Square.NORMAL) {
                setModifier(adversaryList.get(i), square.get(i).name(), square.get(i).getText());
            } else {
                removeModifier(adversaryList.get(i));
            }

            // Displays the last word play by the opponent
            if (isYourTurn) {
                if (!listFrom.isEmpty() && i < listFrom.size()) {
                    adversaryList.get(i).getChildren().add(1, new GTile(listFrom.get(i)));
                    if (square.get(i) != Composition.Square.NORMAL) {
                        adversaryList.get(i).getStyleClass().add("covered");
                    }
                }
            }
        }
    }

    /**
     * Update the user composition with correct bonus on the StackPane
     *
     * @param square list of bonus for the composition
     */
    private void updateComposition(List<Composition.Square> square) {
        for (int i = 0; i < userList.size(); i++) {
            if (square.get(i) != Composition.Square.NORMAL) {
                setModifier(userList.get(i), square.get(i).name(), square.get(i).getText());
            } else {
                removeModifier(userList.get(i));
            }
        }
    }

    /**
     * Cleans the given list of StackPanes, means takes out the bonus and Tiles
     *
     * @param list list to clean
     */
    private void cleanList(List<StackPane> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getChildren().size() < 2) {
                continue;
            }
            list.get(i).getChildren().remove(1);
            list.get(i).getStyleClass().remove("covered");
        }
    }

    /**
     * Update all the board given a game to display
     *
     * @param g game to display
     */
    private void updatePlayer(Game g) {
        updateComposition(g.getSquare());
        updateList(g.getLetters(), deckList);
        updateList(g.getBonusLetters(), userBonusList);
        updateOpponentComposition(g.isYourTurn(), g.getLastWordPlayed(), g.getOpponentSquare());
        updateList(g.getOpponentBonusLetters(), adversaryBonusList);

        // Makes sure the list are cleared
        if (g.isYourTurn()) {
            cleanList(userList);
            cleanList(adversaryBonusList);
        } else {
            cleanList(userBonusList);
        }
    }

    /**
     * Updates the info of a game, sets the button to disable if it is not the user turn
     *
     * @param g game
     */
    public void updateGame(Game g) {
        gameID = g.getID();
        if (!g.isYourTurn()) {
            btnPlay.setDisable(true);
            btnDiscard.setDisable(true);
        } else {
            btnPlay.setDisable(false);
            btnDiscard.setDisable(false);
        }

        // Call to the UI thread to display new infos
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

    /**
     * Return the composition the user want to play
     *
     * @return Composition to play
     */
    private Composition getPlay() {
        Composition composition = new Composition();
        for (StackPane st : userList) {
            if (st.getChildren().size() >= 2) {
                st.getStyleClass().remove("covered");
                composition.push(((GTile) st.getChildren().get(1)).getModel());
            }
        }
        cleanList(adversaryBonusList);
        cleanList(userList);

        // Redisplay in case word is not valid
        updateGame(mainController.getLobyController().getGameFromId(gameID));
        return composition;
    }

    /**
     * Default Getter for the ID of the current displayed game
     *
     * @return int ID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Shuffle the Tiles in the hand of the user, no call to server it is just diplayed
     * in another way
     */
    private void shuffleHand() {
        ArrayList<Tile> temp = new ArrayList<>();
        for (StackPane st : deckList) {
            if (st.getChildren().size() < 2) {
                continue;
            }
            temp.add(((GTile) st.getChildren().get(1)).getModel());
        }
        Collections.shuffle(temp);
        updateList(temp, deckList);
    }

    /**
     * Display if the game is ended
     *
     * @param result Result of the game
     */
    public void displayEndState(Result result) {
        btnPlay.setDisable(true);
        btnDiscard.setDisable(true);
        btnSwap.setDisable(true);
        Platform.runLater(() -> {
                    switch (result) {
                        case WIN:
                            remainingTiles.setText(String.valueOf(ENDWIN));
                            break;
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
