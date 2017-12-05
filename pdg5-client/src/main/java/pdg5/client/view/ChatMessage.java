package pdg5.client.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ChatMessage extends StackPane {
    public ChatMessage(String type, String msg) {
        super(new Label(msg));
        this.getStyleClass().add(type);
    }

    public interface Type {
        String TIME = "time";
        String USER = "user";
        String INFO = "info";
        String ADVERSARY = "adversary";
    }
}
