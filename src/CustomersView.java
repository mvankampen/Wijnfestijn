import javafx.scene.layout.AnchorPane;

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
        setMinSize(1200, 800);

    }
}

