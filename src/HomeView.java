
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class HomeView extends AnchorPane implements ControlledScreen{

	private ScreensController screensController;
	private Button home;
	private Button mailMenu;
	private Rectangle rectangle;
	private Label bericht;
	private NavigationController navigationController;
	@Override
	public void setSchermManager(ScreensController schermController) {
		this.screensController = screensController;	
	}

	public HomeView(NavigationController navigationController) {
		this.navigationController = navigationController;
		maakView();
	}
	private void maakView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		bericht = new Label("dennisi");
		bericht.setFont(new Font("Consolas", 20));
		getChildren().add(bericht);
		getChildren().add(navigationController);
		
	}
}
