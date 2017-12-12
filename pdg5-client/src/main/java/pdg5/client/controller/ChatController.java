package pdg5.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import pdg5.client.view.ChatMessage;
import pdg5.common.Protocol;

public class ChatController {
    @FXML
    private VBox msgContainer;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        Label time = new Label("19 sept. 2017 à 10:46:02");
        ChatMessage msg0 = new ChatMessage(ChatMessage.Type.TIME, "19 sept. 2017 à 10:46:02");
        ChatMessage msg1 = new ChatMessage(ChatMessage.Type.INFO, "Game has started !");
        ChatMessage msg2 = new ChatMessage(ChatMessage.Type.USER, "Hello <3");
        ChatMessage msg3 = new ChatMessage(ChatMessage.Type.ADVERSARY, "Hi");


        addMessage(msg0);
        addMessage(msg1);
        addMessage(msg2);
        addMessage(msg3);


        for(int i=0; i<20; i++) {
            addMessage(new ChatMessage(ChatMessage.Type.ADVERSARY, "Hi bitch"));
        }
    }

    private void addMessage(ChatMessage msg) {
        msgContainer.getChildren().add(msg);

        // Remove old messages
        if(msgContainer.getChildren().size() > Protocol.DISPLAY_CHAT_MESSAGES) {
            msgContainer.getChildren().remove(0);
        }

        // Auto scroll
        scrollPane.setVvalue(1.0);
    }
}
