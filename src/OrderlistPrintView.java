import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class OrderlistPrintView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    private Label lblSummary;
    private Label lblCountText;
    private Label lblCount;
    private Label lblSelectText;
    private TextField txtInput;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }
    public OrderlistPrintView() {
        maakView();
    }

    private void maakView() {
    	getStyleClass().add("background");
		setMinSize(1200,800);
        lblSummary = new Label("Hier kunt u de gepersonaliseerde bestellijsten uit printen voor de klanten.");
        lblCountText = new Label("Aantal bestellingen die geprint moeten worden:");
        lblCount = new Label("10");
        lblSelectText = new Label("Selecteer welke bestellijst u wilt uit printen");
        txtInput = new TextField();
        getChildren().addAll(lblSummary,lblCountText, lblCount, lblSelectText, txtInput);
    }

 
}
