package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MailView extends AnchorPane implements ControlledScreen{

	private ScreensController screensController;

public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;	
	}

	public MailView() {
		createView();
	}
	
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		setUpContentPane();
	}
	
	public void setUpContentPane(){
		//contentPane details
		GridPane contentPane = new GridPane();
		contentPane.setLayoutY(175);
		contentPane.setLayoutX(100);
		contentPane.setVgap(25);
		contentPane.setHgap(150);
		
		//Intro label
		String introText = "Hier kunt u de mails opstellen. Kies welke mail u wilt"
				+ " opstellen en voer\nde titel en body in. Klik op verzenden en de applicatie"
				+ " doet de rest";
		Label introLabel = new Label(introText);
		
		//Create VBoxes row 1
		VBox titleBox = new VBox(15);
		VBox bodyBox = new VBox(15);
		
		//Create VBoxes row 2
		VBox typeBox = new VBox(10);
		
		//Label texts row 1
		String titleText = "Vul hier de titel in:";
		String bodyText = "Vul hier de body in";
		
		//Label texts row 2
		String typeText = "Selecteer wat voor een soort\nmail u wilt verzenden";
		String selectionText = "Met deze selectie wordt de\nmail verstuur naar:";
		String remindMailRbText = "Herinneringsmail";
		String inviteMailRbText = "Uitnodigingsmail";
		String thankMailRbText = "Bedankmail";
		//Creating items row 1
		Label titleLabel = new Label(titleText);
		Label bodyLabel = new Label(bodyText);
		TextField titleTextArea = new TextField();
		TextArea bodyTextArea = new TextArea();
		Label selectionLabel = new Label(selectionText);
		
		//Creating items row 2
		Label typeLabel = new Label(typeText);
		final ToggleGroup inviteGroup = new ToggleGroup();
		RadioButton remindMailRb = new RadioButton(remindMailRbText);
		remindMailRb.setToggleGroup(inviteGroup);
		remindMailRb.getStyleClass().add("rbbuttons");
		RadioButton inviteMailRb = new RadioButton(inviteMailRbText);
		inviteMailRb.setToggleGroup(inviteGroup);
		inviteMailRb.getStyleClass().add("rbbuttons");
		RadioButton thankMailRb = new RadioButton(thankMailRbText);
		thankMailRb.setToggleGroup(inviteGroup);
		thankMailRb.getStyleClass().add("rbbuttons");
		Button printButton = new Button("Printen");
		printButton.getStyleClass().add("form_buttons");
		
		//Adding to VBoxes
		titleBox.getChildren().addAll(titleLabel, titleTextArea);
		bodyBox.getChildren().addAll(bodyLabel, bodyTextArea);
		typeBox.getChildren().addAll(remindMailRb, inviteMailRb, thankMailRb);
		
		//Adding items row 1
		contentPane.add(introLabel, 0, 1);
		contentPane.add(titleBox, 0, 2);
		contentPane.add(bodyBox, 0, 3);
		contentPane.add(typeLabel, 1, 1);
		contentPane.add(typeBox, 1, 2);
		contentPane.add(selectionLabel, 1, 3);
		contentPane.add(printButton, 1, 4);
		
		//Add the contentPane
		getChildren().add(contentPane);
	}
}
