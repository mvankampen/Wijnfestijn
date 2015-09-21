import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class OrderListView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    private Label lblSummary;
    private Label lblCountText;
    private Label lblCount;
    private Label lblSelectText;
    private TextField txtInput;
    private ImageView ivExample;

    public OrderListView() {
        maakView();
    }

    private void maakView() {
        getStylesheets().add("style/style.css");
        getStyleClass().add("achtergrond");
        setMinSize(1680, 1050);
        lblSummary = new Label("Hier kunt u de gepersonaliseerde bestellijsten uit printen voor de klanten.");
        lblCountText = new Label("Aantal bestellingen die geprint moeten worden:");
        lblCount = new Label("10");
        lblSelectText = new Label("Selecteer welke bestellijst u wilt uit printen");
        txtInput = new TextField();
        Image placeholder = new Image("Images/PlaceholderImage.png");
        ivExample = new ImageView(placeholder);
        getChildren().addAll(this.lblSummary,this.lblCountText, this.lblCount, this.lblSelectText, this.txtInput, this.ivExample);
    }

    @Override
    public void setSchermManager(ScreensController schermController) {
        this.screensController = schermController;
    }
}
