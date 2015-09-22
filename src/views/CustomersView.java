package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomersView extends AnchorPane implements ControlledScreen {

	private ScreensController screensController;

public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;	
	}

	public CustomersView() {
		createView();
	}
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		
	}
}

