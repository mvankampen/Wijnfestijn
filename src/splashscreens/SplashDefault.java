package splashscreens;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashDefault {
	String content = "This isn't a text, just a test";
	String title = "This is a test";
	String header = "This is also a test";
	public String getContextText() {
		return content;	
	}
	public String getTitleText() {
		return title;
	}
	public String getHeaderText() {
		return header;
	}
}
