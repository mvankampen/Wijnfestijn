import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NavigationController extends AnchorPane implements ControlledScreen{
	private Button homeButton, mailButton, customersButton, debtorsButton;
	private ComboBox<String> orderMenu;
	private ObservableList<String> orderOptions;
	private ScreensController screensController;
	private String ORDERLISTID = "orderlist";
	public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;
		
	}
	public NavigationController(){
		generateDropDownOptions();
		createMenu();
	}
	
	public void createMenu(){
		//Home Button
		GridPane navGrid = new GridPane();
		navGrid.setVgap(58);
		HBox hbButtons = new HBox();
		homeButton = new Button("Home");
		
		homeButton.getStyleClass().add("nav_item");
		homeButton.setOnAction(e -> {
			screensController.screenSet(ORDERLISTID);
		});
		//Mail Button
		mailButton = new Button("Mail Menu");
		mailButton.getStyleClass().add("nav_item");
		//Order ComboBox
		orderMenu = new ComboBox<String>(orderOptions);
		orderMenu.setValue("Bestel Menu");
		orderMenu.getStyleClass().add("nav_item");
		//Customer Button
		customersButton = new Button("Klanten Menu");
		customersButton.getStyleClass().add("nav_item");
		//Debtors Button
		debtorsButton = new Button("Debiteuren Menu");
		debtorsButton.getStyleClass().add("nav_item");
		hbButtons.getChildren().addAll(homeButton,mailButton, orderMenu, customersButton,debtorsButton );
		navGrid.add(hbButtons, 2, 2);
		getChildren().addAll(navGrid);
	}
	
	
	private void generateDropDownOptions(){
		orderOptions = FXCollections.observableArrayList("option 1","option 2");// not done
	}



	
}
