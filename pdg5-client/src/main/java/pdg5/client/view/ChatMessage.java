package pdg5.client.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChatMessage extends HBox {
    public ChatMessage(Type type, String msg) {
        Label content = new Label(msg);
        content.setWrapText(true);
        this.getChildren().add(content);

        this.getStyleClass().add(type.getValue());
        this.setFillHeight(false);
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
