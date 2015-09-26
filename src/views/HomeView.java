package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

public class HomeView extends AnchorPane implements ControlledScreen {

	private ScreensController screensController;

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
		Label introLabel = new Label("Dit menu is voorzien van alle directe links naar alle functies."
				+ " Klik op de actie die u wilt ondernemen");
		introBox.getChildren().add(introLabel);

		// Making the hyperlinks row 1
		Hyperlink OrderlistLink = new Hyperlink("Bestellijst opties");
		OrderlistLink.getStyleClass().add("homeview_title");
		Hyperlink orderLink1 = new Hyperlink("Printen persoonlijke bestellijsten");
		orderLink1.getStyleClass().add("default_hyperlink");
		Hyperlink orderLink2 = new Hyperlink("Invoeren van de persoonlijke bestellijsten (maakt orders en facturen)");
		orderLink2.getStyleClass().add("default_hyperlink");
		Hyperlink orderLink3 = new Hyperlink("Maak een nieuwe bestellijst aan(Doormiddel van CSV)");
		orderLink3.getStyleClass().add("default_hyperlink");
		Hyperlink orderLink4 = new Hyperlink("Bewerk of verwijder een al bestaande bestellijst");
		orderLink4.getStyleClass().add("default_hyperlink");
		Hyperlink debtorsTitle = new Hyperlink("Debiteuren opties");
		debtorsTitle.getStyleClass().add("homeview_title");
		Hyperlink debtorLink1 = new Hyperlink("Genereer de debiteurenlijst");
		debtorLink1.getStyleClass().add("default_hyperlink");

		// Setting the directions for the hyperlinks on row 1
		OrderlistLink.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		orderLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		orderLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		orderLink3.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		orderLink4.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		debtorsTitle.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		debtorLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});

		// Making the hyperlinks row 2
		Hyperlink mailTitleLink = new Hyperlink("Mail opties");
		mailTitleLink.getStyleClass().add("homeview_title");
		Hyperlink mailLink1 = new Hyperlink("Maak en verstuur een uitnodigingsmail");
		mailLink1.getStyleClass().add("default_hyperlink");
		Hyperlink mailLink2 = new Hyperlink("Maak en verstuur een bedankmail");
		mailLink2.getStyleClass().add("default_hyperlink");
		Hyperlink mailLink3 = new Hyperlink("Maak en verstuur een herinneringsmail");
		mailLink3.getStyleClass().add("default_hyperlink");
		Hyperlink customerTitleLink = new Hyperlink("Klanten opties");
		customerTitleLink.getStyleClass().add("homeview_title");
		Hyperlink customerLink1 = new Hyperlink("Bewerk of verwijder bestaande klanten");
		customerLink1.getStyleClass().add("default_hyperlink");
		Hyperlink customerLink2 = new Hyperlink("Open het klanten registreren scherm");
		customerLink2.getStyleClass().add("default_hyperlink");

		// Setting the directions for the hyperlinks row 2
		mailTitleLink.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		mailLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		mailLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		mailLink3.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		customerTitleLink.setOnAction(event -> {
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

		// Set the text flow objects in row 1
		TextFlow orderTitleFlow = new TextFlow(OrderlistLink);
		TextFlow orderFlow1 = new TextFlow(orderLink1);
		TextFlow orderFlow2 = new TextFlow(orderLink2);
		TextFlow orderFlow3 = new TextFlow(orderLink3);
		TextFlow orderFlow4 = new TextFlow(orderLink4);
		TextFlow debtorsTitleFlow = new TextFlow(debtorsTitle);
		TextFlow debtorsFlow1 = new TextFlow(debtorLink1);

		// Set the text flow objects in row 2
		TextFlow mailTitleFlow = new TextFlow(mailTitleLink);
		TextFlow mailFlow1 = new TextFlow(mailLink1);
		TextFlow mailFlow2 = new TextFlow(mailLink2);
		TextFlow mailFlow3 = new TextFlow(mailLink3);
		TextFlow customerTitleFlow = new TextFlow(customerTitleLink);
		TextFlow customerFlow1 = new TextFlow(customerLink1);
		TextFlow customerFlow2 = new TextFlow(customerLink2);

		// Adding labels row 1
		optionsPane.add(new Label(), 1, 1);
		optionsPane.add(orderTitleFlow, 1, 2);
		optionsPane.add(orderFlow1, 1, 3);
		optionsPane.add(orderFlow2, 1, 4);
		optionsPane.add(orderFlow3, 1, 5);
		optionsPane.add(orderFlow4, 1, 6);
		optionsPane.add(debtorsTitleFlow, 1, 7);
		optionsPane.add(debtorsFlow1, 1, 8);

		// Adding label row 2
		optionsPane.add(mailTitleFlow, 2, 2);
		optionsPane.add(mailFlow1, 2, 3);
		optionsPane.add(mailFlow2, 2, 4);
		optionsPane.add(mailFlow3, 2, 5);
		optionsPane.add(customerTitleFlow, 2, 7);
		optionsPane.add(customerFlow1, 2, 8);
		optionsPane.add(customerFlow2, 2, 9);

		optionsPane.setHgap(100);
		optionsPane.setVgap(10);

		getChildren().addAll(introBox, optionsPane);
	}
}
