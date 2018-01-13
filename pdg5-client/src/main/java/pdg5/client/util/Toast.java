/**
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : Toast.java
 Auteur(s)   : Andrea Cotza
 Date        : 13.11.2017
 
 But         : <‡ complÈter>
 
 Remarque(s) : <‡ complÈter>
 
 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

package pdg5.client.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import pdg5.common.Protocol;

/**
 * Toast, a small message user friendly apparance
 */
public class Toast {
    private Popup popup;
    private Label label;
    private Stage stage;
    private Timeline timer;

    /**
     * Ctor Default, construct a toast given a stage and its message
     *
     * @param stage stage to display a toast
     * @param message message to display
     */
    public Toast(final Stage stage, final String message) {
        this(stage, message, Protocol.TOAST_DEFAULT_DURATION);
    }

    /**
     * Ctor with timer to fade out
     *
     * @param stage stage to display a toast
     * @param message message to display
     * @param duration timeout fade
     */
    public Toast(final Stage stage, final String message, int duration) {
        this.stage = stage;
        popup = new Popup();

        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        label = new Label(message);
        label.setOnMouseReleased(this::handleEscape);
        label.getStylesheets().add("/css/toast.css");
        label.getStyleClass().add("toast");
        popup.getContent().add(label);

        timer = new Timeline(new KeyFrame(Duration.millis(duration), ae -> popup.hide()));
    }

    /**
     * Behaviour when finish
     *
     * @param event
     */
    private void handleEscape(MouseEvent event) {
        popup.hide();
    }

    /**
     * Behaviour when apparance
     *
     * @param event
     */
    private void handleShown(WindowEvent event) {
        popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
        popup.setY(stage.getY() + stage.getHeight() - Protocol.TOAST_BOTTOM_DIST - popup.getHeight() / 2);
    }

    /**
     * Public Method to call from code
     */
    public void show() {
        popup.setOnShown(this::handleShown);
        timer.play();
        popup.show(stage);
    }
}
