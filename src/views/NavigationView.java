package views;

import controllers.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NavigationView extends AnchorPane{

	private ScreensController screensController;
	
	public Button homeButton, mailButton, settingsButton;
    public ComboBox<String> orderMenu;
    public ComboBox<String> customerMenu;
    public ObservableList<String> orderOptions;
    public ObservableList<String> customerOptions;
    
    // Create names for links
    public final String ORDERTITLE = "Bestellijsten";
    public final String ORDER1 = "Print lijsten";
    public final String ORDER2 = "Order opstellen";
    public final String CUSTOMERTITLE = "Klanten";
    public final String CUSTOMER1 = "Aanpassen";
    public final String CUSTOMER2 = "Registratie";
    public final String CUSTOMER3 = "Betalingen";

	
	public NavigationView(){
		createView();
	}
	
	public void createView(){
		generateContentGrid();
	}

	
	public void generateContentGrid(){
    	orderOptions = FXCollections.observableArrayList(ORDER1, ORDER2);
        customerOptions = FXCollections.observableArrayList(CUSTOMER1, CUSTOMER2, CUSTOMER3);
    	
        //Make the gridpane for the Navigation buttons
        GridPane navGrid = new GridPane();
        HBox hbButtons = new HBox();
        navGrid.setVgap(59);
        navGrid.setHgap(10);
        
        //Home Button
        homeButton = new Button("Home");
        homeButton.getStyleClass().add("nav_item");
        
        //Mail Button
        mailButton = new Button("Mail Menu");
        mailButton.getStyleClass().add("nav_item");
        
        //Order ComboBox
        orderMenu = new ComboBox<>(orderOptions);
        orderMenu.getStyleClass().add("nav_item");
        orderMenu.setValue(ORDERTITLE);
        
        //Customer ComboBox
        customerMenu = new ComboBox<String>(customerOptions);
        customerMenu.setValue(CUSTOMERTITLE);
        customerMenu.getStyleClass().add("nav_item");
        
        //Settings Button
        settingsButton = new Button("Instellingen");
        settingsButton.getStyleClass().add("nav_item");
        
        //make the NavBar
        hbButtons.getChildren().addAll(homeButton, mailButton, orderMenu, customerMenu, settingsButton);
        navGrid.add(hbButtons, 2, 2);
        getChildren().addAll(navGrid);
	}
}
