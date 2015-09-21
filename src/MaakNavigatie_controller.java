import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class MaakNavigatie_controller extends HBox implements BeheerstScherm{
	private Button homeButton, mailButton, customersButton, debtorsButton;
	private ComboBox<String> orderMenu;
	private ObservableList<String> orderOptions;
	private SchermenController schermController;
	
	public MaakNavigatie_controller(){
		generateDropDownOptions();
		createMenu();
	}
	
	private void createMenu(){
		//Home Button
		homeButton = new Button("Home");
		homeButton.getStyleClass().add("nav_item");
		homeButton.setOnAction(e -> {
			
			schermController.setScherm(Applet.HOOFDSCHERMID);
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
		
		getStylesheets().add("/Styles/style.css");
		
		getChildren().addAll(homeButton, mailButton, orderMenu, customersButton, debtorsButton);
	}
	
	
	private void generateDropDownOptions(){
		orderOptions = FXCollections.observableArrayList("option 1","option 2");// not done
	}

	@Override
	public void setSchermManager(SchermenController schermController) {
		this.schermController = schermController;
		
	}
}
