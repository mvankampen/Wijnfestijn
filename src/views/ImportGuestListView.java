package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Guest;
import models.Wine;

public class ImportGuestListView extends AnchorPane implements ControlledScreen {
	private Button importButton;
	private Button uploadButton;
	private TableView<Guest> table;
	
	public void setScreenController(ScreensController screensController) {
		
	}
	
	public ImportGuestListView(){
		createView();
		setUpContent();
	}

	public void createView(){
		getStyleClass().add("background");
		setMinSize(1200, 800);
		
	}
	
	public void setUpContent(){
		
		importButton = new Button("Kies .CSV bestand");
		importButton.getStyleClass().add("form_buttons");
		importButton.setAlignment(Pos.CENTER);
		
		uploadButton = new Button("Stuur data naar de database");
		uploadButton.getStyleClass().add("form_buttons");
		table = new TableView<Guest>();
		
		VBox contentBox = new VBox(20);
		contentBox.getChildren().addAll(importButton, uploadButton, table);
		
		BorderPane bPane = new BorderPane();
		bPane.setCenter(contentBox);
		bPane.setLayoutX(100);
		bPane.setLayoutY(200);
		
		this.getChildren().add(bPane);
	}
	public TableView<Guest> getTable(){
		return table;
		
	}
	public Button getUploadButton() {
		return uploadButton;
	}
	public Button getImportButton(){
		return importButton;
	}
}
