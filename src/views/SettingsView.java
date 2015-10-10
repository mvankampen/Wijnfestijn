package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 * Created by Dennis Sloove on 21-9-2015.
 */
public class SettingsView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    public TextField changeEmailField;
    public ComboBox<String> templatesComboBox;
    public TextArea templateArea;
    public Button saveButton, resetButton;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public SettingsView() {
        createView();
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    public void setUpContentPane() {
    	ObservableList<String> options = FXCollections.observableArrayList("Herinnering",
    		        "Uitnodiging", "Bedank", "Factuur herinnering");
    	
    	//Create items
    	changeEmailField = new TextField();
    	templatesComboBox = new ComboBox<String>(options);
    	templatesComboBox.setValue("Templates");
    	templateArea = new TextArea();
    	templateArea.setEditable(false);
    	saveButton = new Button("Opslaan");
    	saveButton.getStyleClass().add("form_buttons");
    	resetButton = new Button("Reset deze template");
    	resetButton.getStyleClass().add("form_buttons");
    	
    	VBox contentVBox = new VBox(25);
    	
    	HBox emailHBox = new HBox(20);
    	emailHBox.getChildren().addAll(changeEmailField);
    	
    	HBox templatesHBox = new HBox(20);
    	templatesHBox.getChildren().addAll(templatesComboBox, templateArea, resetButton);
    	
    	HBox saveButtonBox = new HBox(20);
    	saveButtonBox.getChildren().addAll(saveButton);
    	
    	contentVBox.getChildren().addAll(new Label("Wijzig het standaard e-mail adres "),
    			emailHBox, new Label("Wijzig de templates: "), templatesHBox, saveButtonBox);
    	
    	
    	//Create a BorderPane
    	BorderPane bPane = new BorderPane();
    	bPane.setCenter(contentVBox);
    	bPane.setLayoutX(100);
        bPane.setLayoutY(200);
    	
    	getChildren().addAll(bPane);
    }
}