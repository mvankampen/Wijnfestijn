package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class HomeView extends AnchorPane implements ControlledScreen {

	private ScreensController screensController;
	
	// Create Hyperlinks to give to controller row 1
	Label orderlistTitle, mailTitle;
	public Hyperlink orderLink1, orderLink2, orderLink3, orderLink4;
	// Create Hyperlinks to give to controller row 2
	Label customerTitle, settingsTitle;
	public Hyperlink mailLink1, customerLink1, customerLink2, customerLink3, settingsLink1;

	public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;
	}

	public HomeView() {
		createView();
	}

	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200, 800);
		setUpContentPane();
	}

	public void setUpContentPane() {
		// Create introduction HBox
		HBox introBox = new HBox();
		introBox.setLayoutY(200);
		introBox.setLayoutX(100);
		// Create options Pane
		GridPane optionsPane = new GridPane();
		optionsPane.setLayoutY(200);

		// intro Box
		Label introLabel = new Label("Dit menu is voorzien van directe links naar alle functies."
				+ " Klik op de actie die u wilt ondernemen:");
		introBox.getChildren().add(introLabel);

		// Creating items row 1
		orderlistTitle = new Label("Bestellijst opties");
		orderlistTitle.getStyleClass().add("settings_title");
		orderLink1 = new Hyperlink("Printen persoonlijke bestellijsten");
		orderLink1.getStyleClass().add("default_hyperlink");
		orderLink2 = new Hyperlink("Invoeren van de persoonlijke bestellijsten (maakt orders en facturen)");
		orderLink2.getStyleClass().add("default_hyperlink");
		orderLink3 = new Hyperlink("Maak een nieuwe bestellijst aan (doormiddel van een .CSV bestand)");
		orderLink3.getStyleClass().add("default_hyperlink");
		orderLink4 = new Hyperlink("Bewerk of verwijder een bestaande bestellijst");
		orderLink4.getStyleClass().add("default_hyperlink");
		mailTitle = new Label("Mail opties");
		mailTitle.getStyleClass().add("settings_title");
		mailLink1 = new Hyperlink("Maak en verstuur een e-mail");
		mailLink1.getStyleClass().add("default_hyperlink");

		// Creating items row 2
		customerTitle = new Label("Klanten opties");
		customerTitle.getStyleClass().add("settings_title");
		customerLink1 = new Hyperlink("Bewerk of verwijder bestaande klanten");
		customerLink1.getStyleClass().add("default_hyperlink");
		customerLink2 = new Hyperlink("Open het klanten registreren scherm");
		customerLink2.getStyleClass().add("default_hyperlink");
		customerLink3 = new Hyperlink("Onbetaalde rekeningen");
		customerLink3.getStyleClass().add("default_hyperlink");
		settingsTitle = new Label("Instellingen");
		settingsTitle.getStyleClass().add("settings_title");
		settingsLink1 = new Hyperlink("Wijzig uw instellingen");
		settingsLink1.getStyleClass().add("default_hyperlink");

		// Set the text flow objects in row 1
		TextFlow orderFlow1 = new TextFlow(orderLink1);
		TextFlow orderFlow2 = new TextFlow(orderLink2);
		TextFlow orderFlow3 = new TextFlow(orderLink3);
		TextFlow orderFlow4 = new TextFlow(orderLink4);
		TextFlow mailFlow1 = new TextFlow(mailLink1);

		// Set the text flow objects in row 2
		TextFlow customerFlow1 = new TextFlow(customerLink1);
		TextFlow customerFlow2 = new TextFlow(customerLink2);
		TextFlow customerFlow3 = new TextFlow(customerLink3);
		TextFlow settingsFlow1 = new TextFlow(settingsLink1);

		// Adding labels row 1
		optionsPane.add(new Label(), 1, 1);
		optionsPane.add(orderlistTitle, 1, 2);
		optionsPane.add(orderFlow1, 1, 3);
		optionsPane.add(orderFlow2, 1, 4);
		optionsPane.add(orderFlow3, 1, 5);
		optionsPane.add(orderFlow4, 1, 6);
		optionsPane.add(mailTitle, 1, 7);
		optionsPane.add(mailFlow1, 1, 8);

		// Adding label row 2
		optionsPane.add(customerTitle, 2, 2);
		optionsPane.add(customerFlow1, 2, 3);
		optionsPane.add(customerFlow2, 2, 4);
		optionsPane.add(customerFlow3, 2, 5);
		optionsPane.add(settingsTitle, 2, 7);
		optionsPane.add(settingsFlow1, 2, 8);

		optionsPane.setHgap(100);
		optionsPane.setVgap(10);

		getChildren().addAll(introBox, optionsPane);
	}
}
