package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NavigationView extends AnchorPane{
	
	private Button homeButton, mailButton, settingsButton;
    private ComboBox<String> orderMenu;
    private ComboBox<String> customerMenu;
    private ObservableList<String> orderOptions;
    private ObservableList<String> customerOptions;
    
    // Create names for links
    public final String ORDERTITLE = "Bestellijsten";
    public final String ORDER1 = "Print lijsten";
    public final String ORDER2 = "Order opstellen";
    public final String ORDER3 = "Importeer wijnen";
    public final String ORDER4 = "Importeer gasten";
    public final String CUSTOMERTITLE = "Gasten";
    public final String GUEST1 = "Aanpassen";
    public final String GUEST2 = "Registratie";
    public final String GUEST3 = "Betalingen";
    public final String GUEST4 = "Presentie";

	
	public NavigationView(){
		createView();
	}
	
	public void createView(){
		generateContentGrid();
	}

	
	public void generateContentGrid(){
    	orderOptions = FXCollections.observableArrayList(ORDER1, ORDER2, ORDER3, ORDER4);
        customerOptions = FXCollections.observableArrayList(GUEST1, GUEST2, GUEST3, GUEST4);
    	
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
        setOrderMenu(new ComboBox<>(orderOptions));
        getOrderMenu().getStyleClass().add("nav_item");
        getOrderMenu().setValue(ORDERTITLE);
        
        //Customer ComboBox
        setCustomerMenu(new ComboBox<String>(customerOptions));
        getCustomerMenu().setValue(CUSTOMERTITLE);
        getCustomerMenu().getStyleClass().add("nav_item");
        
        //Settings Button
        settingsButton = new Button("Instellingen");
        settingsButton.getStyleClass().add("nav_item");
        
        //make the NavBar
        hbButtons.getChildren().addAll(homeButton, mailButton, getOrderMenu(), getCustomerMenu(), settingsButton);
        navGrid.add(hbButtons, 2, 2);
        getChildren().addAll(navGrid);
	}

	public ComboBox<String> getCustomerMenu() {
		return customerMenu;
	}

	public void setCustomerMenu(ComboBox<String> customerMenu) {
		this.customerMenu = customerMenu;
	}

	public ComboBox<String> getOrderMenu() {
		return orderMenu;
	}

	public void setOrderMenu(ComboBox<String> orderMenu) {
		this.orderMenu = orderMenu;
	}
	public Button getHomeButton() {
		return homeButton;
	}
	public Button getMailButton() {
		return mailButton;
	}
	public Button getSettingsButton() {
		return settingsButton;
	}
}
