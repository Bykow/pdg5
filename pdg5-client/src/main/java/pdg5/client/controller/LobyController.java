package pdg5.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pdg5.client.ClientSender;
import pdg5.common.protocol.NewGame;

public class LobyController {

    private Label titleToPlay;
    private Label titleWaiting;
    private Label titleFinished;

    private ClientSender sender;
    
    @FXML
    private VBox gameList;

    public LobyController(ClientSender sender) {
        titleToPlay = new Label("A ton tour");
        titleToPlay.getStyleClass().add("titleToPlay");
        titleWaiting = new Label("En attente");
        titleWaiting.getStyleClass().add("titleWaiting");
        titleFinished = new Label("Terminé");
        titleFinished.getStyleClass().add("titleFinished");
        
        this.sender = sender;
    }

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {
        gameList.getChildren().add(titleToPlay);
        gameList.getChildren().add(titleWaiting);
        gameList.getChildren().add(titleFinished);
    }

    @FXML
    private void startNewGame(ActionEvent actionEvent) {
        System.out.println("new game");
        sender.add(new NewGame());
    }
}
