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

/**
 * Controller for the chat (right of the window)
 */
public class ChatController {

    // Container of the messages
    @FXML
    private VBox msgContainer;

    // Scrolling panel
    @FXML
    private ScrollPane scrollPane;

    // Input window to send new messages
    @FXML
    TextField msgInput;

    // Sender, used to send message to server
    private ClientSender sender;

    /**
     * Ctor
     *
     * @param sender used to send message to server
     */
    public ChatController(ClientSender sender) {
        this.sender = sender;
    }

    /**
     * Needed empty initializer for JavaFx
     */
    @FXML
    public void initialize() {
    }

    /**
     * Triggers when the user clics on the send button.
     * Sends the message in the chatbox input
     *
     * @param actionEvent trigger when the button is clicked
     */
    @FXML
    private void sendMsg(ActionEvent actionEvent) {
        String msg = msgInput.getText();
        // cleans the input box
        msgInput.clear();
        // Displays the message directly in the user chat
        addMessage(new ChatMessage(ChatMessage.Type.USER, msg));
        // Sends the message to the server
        sender.add(new Chat(msg, UserInformations.getInstance().getIdGameDisplayed()));
    }

    /**
     * Adds a ChatMessage to the scrollview.
     *
     * @param msg to display
     */
    private void addMessage(ChatMessage msg) {
        msgContainer.getChildren().add(msg);

        // Remove old messages
        if(msgContainer.getChildren().size() > Protocol.DISPLAY_CHAT_MESSAGES) {
            msgContainer.getChildren().remove(0);
        }

        // Auto scroll
        scrollPane.setVvalue(1.0);
    }

    /**
     * Displays all the chat history for a given game
     *
     * @param list history of chat to display
     * @param game concerned game
     */
    public void displayChat(List<Chat> list, Game game) {
        // Clears the chat of other message
        cleanChat();
        // Displays the start time of the game
        addMessage(new ChatMessage(ChatMessage.Type.TIME, game.getCreated().toString()));
        if (list != null) {
            for (Chat c : list) {
                addChat(c);
            }
        }
    }

    /**
     * Adds a single Chat to the scrollview
     *
     * @param c Chat to add
     */
    public void addChat(Chat c) {
        Platform.runLater(() -> addMessage(convertChatToChatMessage(c)));
    }

    /**
     * Converts a Chat to a ChatMessage, the object the scrollview is using.
     *
     * @param c Chat to convert
     * @return ChatMessage
     */
    private ChatMessage convertChatToChatMessage (Chat c) {
        ChatMessage.Type type;

        // Where does is display, left or right
        if (c.getSender() == Chat.SENDER.USER) {
            type = ChatMessage.Type.USER;
        } else {
            type = ChatMessage.Type.ADVERSARY;
        }

        return new ChatMessage(type, c.getMessage());
    }

    /**
     * Cleans the scrollview from all ChatMessage
     */
    private void cleanChat() {
        msgContainer.getChildren().clear();
    }
}
