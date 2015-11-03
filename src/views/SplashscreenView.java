package views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Created by Alex on 07-10-15.
 */
// View for the collected messages that needs to be seen by the user
public class SplashscreenView {
	public SplashscreenView(String title, String header, String context) {
		Alert splashscreen = new Alert(AlertType.INFORMATION);
		splashscreen.setTitle(title);
		splashscreen.setHeaderText(header);
		splashscreen.setContentText(context);
		splashscreen.showAndWait();
	}
}
