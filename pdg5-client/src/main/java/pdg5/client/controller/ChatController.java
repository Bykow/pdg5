package pdg5.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pdg5.client.ClientSender;
import pdg5.client.util.UserInformations;
import pdg5.client.view.ChatMessage;
import pdg5.common.Protocol;
import pdg5.common.protocol.Chat;
import pdg5.common.protocol.Game;

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
    }

    @FXML
    private void sendMsg(ActionEvent actionEvent) {
        String msg = msgInput.getText();
        msgInput.clear();
        System.out.println("Trying to send: " + msg);
        addMessage(new ChatMessage(ChatMessage.Type.USER, msg));
        sender.add(new Chat(msg, UserInformations.getInstance().getIdGameDisplayed()));
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

    public void displayChat(List<Chat> chat, Game game) {
        cleanChat();
        addMessage(new ChatMessage(ChatMessage.Type.TIME, game.getCreated().toString()));
        if (chat != null) {
            for (Chat c : chat) {
                addChat(c);
            }
        }
    }

    public void addChat(Chat c) {
        Platform.runLater(() -> addMessage(convertChatToChatMessage(c)));
    }

    private ChatMessage convertChatToChatMessage (Chat c) {
        ChatMessage.Type type;

        if (c.getSender() == Chat.SENDER.USER) {
            type = ChatMessage.Type.USER;
        } else {
            type = ChatMessage.Type.ADVERSARY;
        }

        return new ChatMessage(type, c.getMessage());
    }

    private void cleanChat() {
        msgContainer.getChildren().clear();
    }
}
