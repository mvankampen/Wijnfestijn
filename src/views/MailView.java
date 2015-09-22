package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MailView extends AnchorPane implements ControlledScreen {

    private ScreensController screensController;
    private Label TestLabel;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public MailView() {
        createView();
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        TestLabel = new Label("This is t3st");
        TestLabel.setLayoutY(200);
        TestLabel.setLayoutX(20);
        getChildren().add(TestLabel);
    }
}
