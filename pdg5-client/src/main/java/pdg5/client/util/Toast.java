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

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pdg5.common.Protocol;

public class Toast {
    Popup popup;
    Label label;
    Stage stage;

    public Toast(final Stage stage, final String message) {
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
    }

    private void handleEscape(MouseEvent event) {
        popup.hide();
    }

    private void handleShown(WindowEvent event) {
        popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
        popup.setY(stage.getY() + stage.getHeight() - Protocol.TOAST_BOTTOM_DIST - popup.getHeight() / 2);
    }

    public void show() {
        popup.setOnShown(this::handleShown);
        popup.show(stage);
    }
}
