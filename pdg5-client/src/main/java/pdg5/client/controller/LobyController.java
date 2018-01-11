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
import pdg5.common.protocol.Chat;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.NewGame;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LobyController extends AbstractController {
    private Label titleToPlay;
    private Label titleWaiting;
    private Label titleFinished;

    private ArrayList<Game> gameModelList;
    private Map<Integer, List<Chat> > historic;

    private GGameListEntry selected;
    private GameController gameController;
    private ChatController chatController;

    private ClientSender sender;

    @FXML
    private VBox gameList;

    @FXML
    private Label username;

    public LobyController(ClientSender sender, GameController gameController, ChatController chatController) {
        gameModelList = new ArrayList<>();

        titleToPlay = new Label("A ton tour");
        titleToPlay.getStyleClass().add("titleToPlay");
        titleWaiting = new Label("En attente");
        titleWaiting.getStyleClass().add("titleWaiting");
        titleFinished = new Label("Terminé");
        titleFinished.getStyleClass().add("titleFinished");

        historic = new HashMap<>();
        
        this.sender = sender;
        this.gameController = gameController;
        this.chatController = chatController;
    }

    @FXML
    public void initialize() {
        username.setText(UserInformations.getInstance().getUsername());
        refresh();
    }

    private List<GGameListEntry> genGraphicalEntry(Predicate<Game> condition) {
        return gameModelList.stream()
                .filter(condition)
                .sorted(Comparator.comparing(Game::getLastActivity))
                .map(m -> new GGameListEntry(m, this::handleMouseClick, this::handleDelete))
                .collect(Collectors.toList());
    }

    public void refresh() {
        gameList.getChildren().clear();

        gameList.getChildren().add(titleToPlay);
        gameList.getChildren().addAll(genGraphicalEntry(g -> g.isYourTurn() && g.getState() == GameModel.State.IN_PROGRESS));

        gameList.getChildren().add(titleWaiting);
        gameList.getChildren().addAll(genGraphicalEntry(g -> !g.isYourTurn() && g.getState() == GameModel.State.IN_PROGRESS));

        gameList.getChildren().add(titleFinished);
        gameList.getChildren().addAll(genGraphicalEntry(g -> g.getState() == GameModel.State.FINISHED));

        if(selected != null) {
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

    private void unselectLast() {
        if(selected != null)
            selected.setSelected(false);
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        //TODO Logout
        System.out.println("logout");
    }

    @FXML
    private void startNewGame(ActionEvent actionEvent) {
        System.out.println("new game");
        sender.add(new NewGame());
    }

    private void handleMouseClick(MouseEvent event) {
        GGameListEntry element = (GGameListEntry)event.getSource();
        unselectLast();
        selected = element;
        element.setSelected(true);
        UserInformations.getInstance().setIdGameDisplayed(selected.getModel().getID());
        gameController.updateGame(element.getModel());
        chatController.displayChat(historic.get(selected.getModel().getID()), selected.getModel());
    }

    private void handleDelete(ActionEvent event) {
        Node btn = (Node)event.getSource();
        GGameListEntry element = (GGameListEntry)btn.getParent();
        System.out.println("delete request for " + element.getModel().getID());
    }

    public void addLoad(Load load) {
        historic = load.getHistoric();
        for (Game g: load.getGames()) {
            addGame(g);
        }
    }

    private void addGame(Game game) {
        gameModelList.add(game);
    }

    public boolean hasGame(Game game) {
        return gameModelList.stream().anyMatch((o) -> o.getID() == game.getID());
    }

    public void updateGame(Game game) {
        gameModelList.removeIf((o) -> o.getID() == game.getID());
        addGame(game);
        if (gameController.getGameID() == game.getID()) {
            gameController.updateGame(game);
        }

        //if (historic.get(game.getID()))
        Platform.runLater(this::refresh);
    }

    public void updateChat(Chat chat) {
        historic.get(chat.getGameId()).add(chat);
        if (chat.getGameId() == selected.getModel().getID()) {
            chatController.addChat(chat, selected.getModel());
        }
    }
}
