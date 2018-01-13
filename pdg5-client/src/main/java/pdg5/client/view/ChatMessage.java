package pdg5.client.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Graphical representation of a Chat Message
 */
public class ChatMessage extends AnchorPane {
    public ChatMessage(Type type, String msg) {
        TextFlow tf = new TextFlow(new Text(msg));
        this.getChildren().add(tf);

        switch(type) {
            case TIME:
            case INFO:
                AnchorPane.setRightAnchor(tf, 0.0);
                AnchorPane.setLeftAnchor(tf, 0.0);
                break;
            case USER:
                AnchorPane.setRightAnchor(tf, 0.0);
                break;
            case ADVERSARY:
                AnchorPane.setLeftAnchor(tf, 0.0);
                break;
        }

        this.getStyleClass().add(type.getValue());
    }

    /**
     * Enum that defines where and how to print the message
     */
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
