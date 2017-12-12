package pdg5.client.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ChatMessage extends StackPane {
    public ChatMessage(Type type, String msg) {
        super(new Label(msg));
        this.getStyleClass().add(type.getValue());
    }

    public enum Type {
        TIME("time"),
        USER("user"),
        INFO("info"),
        ADVERSARY("adversary");

        private String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
