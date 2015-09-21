import javafx.scene.layout.AnchorPane;

public class MailView extends AnchorPane implements ControlledScreen{

	private ScreensController screensController;

public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;	
	}

	public MailView() {
		createView();
	}
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		
	}
}
