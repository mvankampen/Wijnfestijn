package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Wine;
//This screen is a AnchorPane and uses ControlledScreen as navigation manager
public class ImportWineListView extends AnchorPane implements ControlledScreen {
	private Button importButton;
	private Button uploadButton;
	private TableView<Wine> table;
	
	public void setScreenController(ScreensController screensController) {
		/*
		 * Used for registering itself in the hashMap of the ScreensController
		 * to enable navigation
		 */
	}
	
	public ImportWineListView(){
		// calling the methods that initialize different aspects of the view
		createView();
		setUpContent();
	}
	// adds the style class and sets the fixed height to the screen
	public void createView(){
		getStyleClass().add("background");
		setMinSize(1200, 800);
		
	}
	
	public void setUpContent(){
		// making all buttons
		importButton = new Button("Kies .CSV bestand");
		importButton.getStyleClass().add("form_buttons");
		importButton.setAlignment(Pos.CENTER);
		uploadButton = new Button("Stuur data naar de database");
		uploadButton.getStyleClass().add("form_buttons");
		
		// creates the tableview that displays the uploaded data
		table = new TableView<Wine>();
		// vbox with all the content
		VBox contentBox = new VBox(20);
		contentBox.getChildren().addAll(importButton, uploadButton, table);
		
		BorderPane bPane = new BorderPane();
		bPane.setCenter(contentBox);
		bPane.setLayoutX(100);
		bPane.setLayoutY(200);
		
		this.getChildren().add(bPane);
	}
	//getters
	public TableView<Wine> getTable(){
		return table;
		
	}
	public Button getUploadButton() {
		return uploadButton;
	}
	public Button getImportButton(){
		return importButton;
	}
}
