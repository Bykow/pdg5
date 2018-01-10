package pdg5.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pdg5.client.ClientSender;
import pdg5.client.view.ChatMessage;
import pdg5.common.Protocol;
import pdg5.common.protocol.Chat;
import pdg5.common.protocol.Load;

import java.util.List;

public class ChatController {
    @FXML
    private VBox msgContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    TextField msgInput;

    private ClientSender sender;

    public ChatController(ClientSender sender) {
        this.sender = sender;
    }

    @FXML
    public void initialize() {
        Label time = new Label("19 sept. 2017 à 10:46:02");
        ChatMessage msg0 = new ChatMessage(ChatMessage.Type.TIME, "19 sept.sdfjlskdjf");
        ChatMessage msg1 = new ChatMessage(ChatMessage.Type.INFO, "Game has started !");
        ChatMessage msg2 = new ChatMessage(ChatMessage.Type.USER, "Hello <3");
        ChatMessage msg3 = new ChatMessage(ChatMessage.Type.ADVERSARY, "Hi");

        addMessage(msg0);
        addMessage(msg1);
        addMessage(msg2);
        addMessage(msg3);
    }

    @FXML
    private void sendMsg(ActionEvent actionEvent) {
        ChatMessage cm = new ChatMessage(ChatMessage.Type.USER, msgInput.getText());
        addMessage(cm);
        sender.add(convertChatMessageToChat(cm));
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

    public void displayChat(List<Chat> chat) {
        cleanChat();
        for (Chat c : chat) {
            addChat(c);
        }
    }

    public void addChat(Chat c) {
        addMessage(convertChatToChatMessage(c));
    }

    private ChatMessage convertChatToChatMessage (Chat c) {
        //TODO implement me
        return null;
    }

    private Chat convertChatMessageToChat (ChatMessage cm) {
        //TODO implement me
        return null;
    }

    private void cleanChat() {
        msgContainer.getChildren().clear();
    }
}
