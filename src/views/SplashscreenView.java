package views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * <p> View for the collected (error)messages that needs to be seen by the user </p>
 *
 * @author Alex
 */

public class SplashscreenView {
    /**
     * <p> Creates the alert box with the message/title/header set by the decorator </p>
     *
     * @param title   title that is given by the decorator
     * @param header  header that is given by the decorator
     * @param context context that is given by the decorator
     */
    public SplashscreenView(String title, String header, String context) {
        Alert splashscreen = new Alert(AlertType.INFORMATION);
        splashscreen.setTitle(title);
        splashscreen.setHeaderText(header);
        splashscreen.setContentText(context);
        splashscreen.showAndWait();
    }
}
