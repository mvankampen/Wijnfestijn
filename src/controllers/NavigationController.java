package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import views.Applet;


public class NavigationController extends AnchorPane {
    private Button homeButton, mailButton, debtorsButton;
    private ComboBox<String> orderMenu;
    private ComboBox<TextFlow> customerMenu;
    private ObservableList<String> orderOptions;
    private ObservableList<TextFlow> customerOptions;
    private ScreensController screensController;
    
    private TextFlow orderFlow1, orderFlow2;

    public NavigationController(ScreensController screensController) {
        this.screensController = screensController;
        createNavbar();
    }

    public void createNavbar() {
     generateDropDownOptions();
     generateContentGrid();
    }
    public void generateContentGrid(){
     //Make the gridpane for the Navigation buttons
        GridPane navGrid = new GridPane();
        HBox hbButtons = new HBox();
        navGrid.setVgap(59);
        navGrid.setHgap(10);
        
        //Default text combobox
        Hyperlink orderDefaultLink = new Hyperlink("Bestellijst");
        Hyperlink customerDefaultLink = new Hyperlink("Klanten");
        orderDefaultLink.getStyleClass().add("default_hyperlink");
        customerDefaultLink.getStyleClass().add("default_hyperlink");
        
        //Home Button
        homeButton = new Button("Home");
        homeButton.getStyleClass().add("nav_item");
        //Mail Button
        mailButton = new Button("Mail Menu");
        mailButton.getStyleClass().add("nav_item");
        //Order ComboBox
        orderMenu = new ComboBox<>(orderOptions);
        orderMenu.getStyleClass().add("nav_item");
        orderMenu.setItems(orderOptions);
        orderMenu.setValue("Bestellijst");
        orderMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                	switch(newValue) {
                	case "Bestellijst 1":  linkList1();  break;
                	case "Bestellijst 2":  linkList2();  break;
            	}
                }
            }
        });

//        orderMenu.setOnMouseClicked(t -> {
//            String item = orderMenu.getSelectionModel().getSelectedItem().toString();
//            String flow1 = orderFlow1.parentProperty().getName().toString();
//            String flow2 = orderFlow2.getParent().toString();
//
////            System.out.println(orderMenu.getSelectionModel().selectedItemProperty());
//
//            System.out.println(item + "<-- geklikt item");
//            System.out.println(flow1);
//
//            if (item.equals(flow1)) {
//                System.out.println("geen equals, bitch");
//            }
//            //System.out.println("--" + item + "--" + flow1 + "--" + flow2);
//        });

        //Customer Button
        customerMenu = new ComboBox<TextFlow>(customerOptions);
        customerMenu.setValue(new TextFlow(customerDefaultLink));
        customerMenu.getStyleClass().add("nav_item");
        //Debtors Button
        debtorsButton = new Button("Debiteuren Menu");
        debtorsButton.getStyleClass().add("nav_end");
        /*make lambda event handling*/
        //For the home button, set HomeScreen
        homeButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getHOMEID());
        });
        //For the mail button, set MailScreen
        mailButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getMAILID());
        });
        //For the Customer button, set CustomerScreen
        // debtor button event.
        debtorsButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getDEBITEURENID());
        });
        //make the NavBar
        hbButtons.getChildren().addAll(homeButton, mailButton, orderMenu, 
          customerMenu, debtorsButton);
        navGrid.add(hbButtons, 2, 2);
        getChildren().addAll(navGrid);
}
    //This will be generating the dropdown options, Event handling in progress
    private void generateDropDownOptions() {
    	Hyperlink orderLink1 = new Hyperlink("Bestellijst 1");
    	Hyperlink orderLink2 = new Hyperlink("Bestellijst 2");
    	orderLink1.getStyleClass().add("default_hyperlink");
    	orderLink2.getStyleClass().add("default_hyperlink");
    	Hyperlink customerLink1 = new Hyperlink("Customer 1");
    	Hyperlink customerLink2 = new Hyperlink("Customer 2");
    	customerLink1.getStyleClass().add("default_hyperlink");
    	customerLink2.getStyleClass().add("default_hyperlink");
    	orderLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
    	
    	orderLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
    	customerLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
    	
    	customerLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
    	orderFlow1 = new TextFlow(orderLink1);
    	orderFlow2 = new TextFlow(orderLink2);
    	TextFlow customerFlow1 = new TextFlow(customerLink1);
    	TextFlow customerFlow2 = new TextFlow(customerLink2);
    	
    	
        orderOptions = FXCollections.observableArrayList("Bestellijst 1", "Bestellijst 2");
        customerOptions = FXCollections.observableArrayList(customerFlow1, customerFlow2);
    }
    
    public void linkList1(){
    	screensController.screenSet(ControllersController.getORDERLISTPRINTID());
    	orderMenu.setValue("Bestellijst");
    }
    
    public void linkList2(){
    	screensController.screenSet(ControllersController.getORDERLISTID());
    	orderMenu.setValue("Bestellijst");
    }
}