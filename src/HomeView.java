import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HomeView extends AnchorPane implements ControlledScreen {

    private ScreensController screensController;
    private Label TestLabel;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public HomeView() {
        createView();
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        TestLabel = new Label("This is test");
        TestLabel.setLayoutY(200);
        getChildren().add(TestLabel);
    }
}

