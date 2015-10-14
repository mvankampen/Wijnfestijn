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
import models.Wine;

public class ImportWineListView extends AnchorPane implements ControlledScreen {
	private ScreensController screensController;
	private Button importButton;
	private TableView<Wine> table;
	
	public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;
		
	}
	
	public ImportWineListView(){
		createView();
	}

	public void createView(){
		getStyleClass().add("background");
		setMinSize(1200, 800);
		setUpContent();
	}
	
	public void setUpContent(){
		
		importButton = new Button("Kies .CSV bestand");
		importButton.getStyleClass().add("form_buttons");
		importButton.setAlignment(Pos.CENTER);
		
		table = new TableView<Wine>();
		
		VBox contentBox = new VBox(20);
		contentBox.getChildren().addAll(importButton, table);
		
		BorderPane bPane = new BorderPane();
		bPane.setCenter(contentBox);
		bPane.setLayoutX(100);
		bPane.setLayoutY(200);
		
		this.getChildren().add(bPane);
	}
	public TableView<Wine> getTable(){
		return table;
		
	}
	public Button getImportButton(){
		return importButton;
	}
}
