package pdg5.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pdg5.client.ClientSender;
import pdg5.client.util.UserInformations;
import pdg5.client.view.GGameListEntry;
import pdg5.common.game.GameModel;
import pdg5.common.protocol.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Controller for the loby
 */
public class LobyController extends AbstractController {
    // Labels for the list of game
    private Label titleToPlay;
    private Label titleWaiting;
    private Label titleFinished;

    // List of games
    private ArrayList<Game> gameModelList;

    // Chat history is managed by the loby
    private Map<Integer, List<Chat>> historic;

    // Graphical list
    private GGameListEntry selected;
    private MainController mainController;

    // Sender used to send message to the server
    private ClientSender sender;

    @FXML
    private VBox gameList;

    @FXML
    private Label username;

    /**
     * Ctor
     *
     * @param sender
     * @param mainController
     */
    public LobyController(ClientSender sender, MainController mainController) {
        gameModelList = new ArrayList<>();

        titleToPlay = new Label("A ton tour");
        titleToPlay.getStyleClass().add("titleToPlay");
        titleWaiting = new Label("En attente");
        titleWaiting.getStyleClass().add("titleWaiting");
        titleFinished = new Label("Terminé");
        titleFinished.getStyleClass().add("titleFinished");

        historic = new HashMap<>();

        this.sender = sender;
        this.mainController = mainController;
    }

    /**
     * Initialize the loby
     */
    @FXML
    public void initialize() {
        // Sets the username from the UserInformations data
        username.setText(UserInformations.getInstance().getUsername());
        refresh();
    }

    /**
     * Generates the list of game for UI thread given the state of the game
     * Sorts the list by time
     *
     * @param state state of the game
     * @return List of games to display
     */
    private List<GGameListEntry> genGraphicalEntry(Predicate<Game> state) {
        return gameModelList.stream()
                .filter(state)
                .sorted(Comparator.comparing(Game::getLastActivity).reversed())
                .map(m -> new GGameListEntry(m, this::handleMouseClick, this::handleDelete))
                .collect(Collectors.toList());
    }

    /**
     * Refreshes the list of games and keep it sorted
     */
    private void refresh() {
        gameList.getChildren().clear();

        gameList.getChildren().add(titleToPlay);
        gameList.getChildren().addAll(genGraphicalEntry(g -> g.isYourTurn() && g.getState() == GameModel.State.IN_PROGRESS));

        gameList.getChildren().add(titleWaiting);
        gameList.getChildren().addAll(genGraphicalEntry(g -> !g.isYourTurn() && g.getState() == GameModel.State.IN_PROGRESS));

        gameList.getChildren().add(titleFinished);
        gameList.getChildren().addAll(genGraphicalEntry(g -> g.getState() == GameModel.State.FINISHED));

        if (selected != null) {
            Optional<Node> selBefRefresh = gameList.getChildren().stream()
                    .filter(g -> g instanceof GGameListEntry)
                    .filter(g -> ((GGameListEntry) g).getModel().getID() == selected.getModel().getID())
                    .findFirst();
            if (selBefRefresh.isPresent()) {
                selected = (GGameListEntry) selBefRefresh.get();
                selected.setSelected(true);
            } else {
                selected = null;
            }
        }
    }

    /**
     * Unselect the last selected item in the list
     */
    private void unselectLast() {
        if (selected != null)
            selected.setSelected(false);
    }

    /**
     * Behaviour on logout button clicked
     *
     * @param actionEvent
     */
    @FXML
    private void logout(ActionEvent actionEvent) {
        // Will bring back the SignIn panel
        mainController.logout();
    }

    /**
     * Behaviour for new game button clicked
     * Server will send a new Game when it is available
     *
     * @param actionEvent
     */
    @FXML
    private void startNewGame(ActionEvent actionEvent) {
        sender.add(new NewGame());
    }

    /**
     * Behaviour when a object is clicked in the list
     *
     * @param event
     */
    private void handleMouseClick(MouseEvent event) {
        GGameListEntry element = (GGameListEntry) event.getSource();
        unselectLast();
        selected = element;
        element.setSelected(true);
        UserInformations.getInstance().setIdGameDisplayed(selected.getModel().getID());
        mainController.getGameController().updateGame(element.getModel());
        // Makes sure if the game is finished, the game is properly displayed
        if (element.getModel().getState() == GameModel.State.FINISHED) {
            mainController.getGameController().displayEndState(element.getModel().getResult());
        }
        // Fetches the chat for given game and displays it
        mainController.getChatController().displayChat(historic.get(selected.getModel().getID()), selected.getModel());
    }

    /**
     * Behaviour when delete button is clicked, only for finished games
     * Currently not implemented.
     *
     * @param event
     */
    private void handleDelete(ActionEvent event) {
        Node btn = (Node) event.getSource();
        GGameListEntry element = (GGameListEntry) btn.getParent();
    }

    /**
     * Process a Load, recieved when the client logs in.
     *
     * @param load Load containing chat history, games
     */
    public void addLoad(Load load) {
        historic.putAll(load.getHistoric());
        addListGame(load.getGames());
    }

    /**
     * Process a list of Games to add to the list, calls a refresh
     *
     * @param list list of games to update
     */
    private void addListGame(List<Game> list) {
        gameModelList.addAll(list);
        gameModelList = gameModelList.stream()
                .sorted(Comparator.comparing(Game::getLastActivity))
                .collect(Collectors.toCollection(ArrayList::new));
        Platform.runLater(this::refresh);
    }

    /**
     * Adds a single game
     *
     * @param game game to add
     */
    private void addGame(Game game) {
        addListGame(Collections.singletonList(game));
    }

    /**
     * Updates the status of a game and displays it if selected in list
     *
     * @param game game to update
     */
    public void updateGame(Game game) {
        gameModelList.removeIf((o) -> o.getID() == game.getID());
        addGame(game);

        if (mainController.getGameController().getGameID() == game.getID()) {
            if (game.getState() == GameModel.State.FINISHED) {
                mainController.getGameController().displayEndState(game.getResult());
            } else {
                mainController.getGameController().updateGame(game);
            }
        }

        if (!historic.containsKey(game.getID())) {
            historic.put(game.getID(), new ArrayList<>());
        }
    }

    /**
     * Updates the chat history, displays it if linked to currently displayed game
     *
     * @param chat chat to process
     */
    public void updateChat(Chat chat) {
        if (!historic.containsKey(chat.getGameId()) || historic.get(chat.getGameId()) == null) {
            historic.put(chat.getGameId(), Collections.singletonList(chat));
        } else {
            historic.get(chat.getGameId()).add(chat);
            if (chat.getGameId() == UserInformations.getInstance().getIdGameDisplayed()) {
                mainController.getChatController().addChat(chat);
            }
        }
    }

    /**
     * Returns a Game given its ID
     *
     * @param id Game Id to fetch
     * @return Game
     */
    public Game getGameFromId(int id) {
        return gameModelList.stream().filter((o) -> o.getID() == id).findAny().get();
    }

    /**
     * Behaviour if Game is finished
     *
     * @param end End message
     */
    public void gameIsFinished(End end) {
        mainController.getGameController().displayEndState(end.getResult());
        getGameFromId(end.getIdGame()).setState(GameModel.State.FINISHED);
        getGameFromId(end.getIdGame()).setResult(end.getResult());
        Platform.runLater(this::refresh);
    }
}
