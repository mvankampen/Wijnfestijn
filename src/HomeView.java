
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class HomeView extends AnchorPane implements ControlledScreen{

	private ScreensController screensController;
	private Button test;
	private Label bericht;
	private NavigationController navigationController;

public void setScreenController(ScreensController screensController) {
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
		test = new Button("test");
		test.setOnAction(e -> {
			screensController.screenSet(Applet.ORDERLISTID);
		});
		bericht.setFont(new Font("Consolas", 20));
		getChildren().add(navigationController);
		getChildren().addAll(bericht,test);
	
		
	}
	}

