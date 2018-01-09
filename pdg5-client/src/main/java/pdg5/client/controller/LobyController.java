package pdg5.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pdg5.client.ClientSender;
import pdg5.client.util.Toast;
import pdg5.client.view.GGameListEntry;
import pdg5.common.game.GameModel;
import pdg5.common.protocol.Game;
import pdg5.common.protocol.Load;
import pdg5.common.protocol.NewGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public class LobyController {

    private Label titleToPlay;
    private Label titleWaiting;
    private Label titleFinished;

    private ArrayList<Game> gameModelList;

    private GGameListEntry selected;
    private GameController gameController;

    private ClientSender sender;
    
    @FXML
    private VBox gameList;

    public LobyController(ClientSender sender, GameController gameController) {
        gameModelList = new ArrayList<>();

        titleToPlay = new Label("A ton tour");
        titleToPlay.getStyleClass().add("titleToPlay");
        titleWaiting = new Label("En attente");
        titleWaiting.getStyleClass().add("titleWaiting");
        titleFinished = new Label("Terminé");
        titleFinished.getStyleClass().add("titleFinished");
        
        this.sender = sender;
        this.gameController = gameController;
    }

    @FXML
    public void initialize() {
        refresh();
    }

    public void refresh() {
        gameList.getChildren().clear();

        gameList.getChildren().add(titleToPlay);
        gameList.getChildren().addAll(gameModelList.stream()
                .filter(g -> g.isYourTurn() && g.getState() == GameModel.State.IN_PROGRESS)
                .sorted(Comparator.comparing(Game::getLastActivity))
                .map(m -> new GGameListEntry(m, this::handleMouseClick, this::handleDelete))
                .collect(Collectors.toList()));

        gameList.getChildren().add(titleWaiting);
        gameList.getChildren().addAll(gameModelList.stream()
                .filter(g -> !g.isYourTurn() && g.getState() == GameModel.State.IN_PROGRESS)
                .sorted(Comparator.comparing(Game::getLastActivity))
                .map(m -> new GGameListEntry(m, this::handleMouseClick, this::handleDelete))
                .collect(Collectors.toList()));

        gameList.getChildren().add(titleFinished);
        gameList.getChildren().addAll(gameModelList.stream()
                .filter(g -> g.getState() == GameModel.State.FINISHED)
                .sorted(Comparator.comparing(Game::getLastActivity))
                .map(m -> new GGameListEntry(m, this::handleMouseClick, this::handleDelete))
                .collect(Collectors.toList()));

        if(selected != null) {
            Optional<Node> selBefRefresh = gameList.getChildren().stream()
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
    private void startNewGame(ActionEvent actionEvent) {
        System.out.println("new game");
        sender.add(new NewGame());
    }

    private void handleMouseClick(MouseEvent event) {
        GGameListEntry element = (GGameListEntry)event.getSource();
        unselectLast();
        selected = element;
        element.setSelected(true);
        gameController.updateGame(element.getModel());
    }

    private void handleDelete(ActionEvent event) {
        Node btn = (Node)event.getSource();
        GGameListEntry element = (GGameListEntry)btn.getParent();
        System.out.println("delete request for " + element.getModel().getID());
    }

    public void addLoad(Load load) {
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
        Platform.runLater(this::refresh);

    }
}
