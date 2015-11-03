package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import models.Mail;

//This screen is a AnchorPane and uses ControlledScreen as navigation manager
public class MailView extends AnchorPane implements ControlledScreen {

    private TextField titleTextArea;
    private Button mailButton = new Button("Verzenden");
    private HTMLEditor htmlEditor;

    private ToggleGroup inviteGroup = new ToggleGroup();
    private Mail mail;

    public void setScreenController(ScreensController screensController) {
    	/*
		 * Used for registering itself in the hashMap of the ScreensController
		 * to enable navigation
		 */
    }

    public MailView(Mail mail) {
        this.mail = mail;
     // calling the methods that initialize different aspects of the view
        createView();
        setUpContentPane();
    }
    //? why is this here
    public void updateFields() {
        this.titleTextArea.setText(this.mail.getSubject());
        this.htmlEditor.setHtmlText(this.mail.getBody());
    }
 // adds the style class and sets the fixed height to the screen
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
    }

    public void setUpContentPane() {
    	// creating the gridpane, this is where all the displayed content goes
        GridPane contentPane = new GridPane();
        contentPane.setLayoutX(100);
        contentPane.setLayoutY(200);
        contentPane.setVgap(10);
        contentPane.setHgap(25);
        /*
		 * creating all Strings, used to make the labels their content easier to
		 * adjust
		 */
		String introText = "Hier kunt u de mails opstellen. Kies welke mail u wilt"
				+ " opstellen en voer\nde titel en body in. Klik op verzenden en de applicatie" + " doet de rest";
		/*
		 * creating all Labels, used to instruct the user towards what actions
		 * he can perform within the view
		 */
		Label introLabel = new Label(introText);
		Label titleLabel = new Label("Vul hier de titel in:");
		Label typeLabel = new Label("Selecteer wat voor een soort\nmail u wilt verzenden");

		/* Creating all vboxes that are used to organize the sectors used in the
		* contentPane
		*/ 
        VBox titleBox = new VBox();
        VBox bodyBox = new VBox();
        VBox typeBox = new VBox();
        // creating the buttons and setting their properties
        mailButton = new Button("Verzenden");
        this.mailButton = new Button("Verzenden");
        mailButton.getStyleClass().add("form_buttons");
        // creating the textareas
        titleTextArea = new TextField();
        // creating the HTMLeditor so the user can see HTMLstyling
        htmlEditor = new HTMLEditor();
        htmlEditor.setMaxHeight(300);
        
        // setting all radiobuttons, used to toggle which mail needs to be seen
        RadioButton remindMailRb = new RadioButton("Herinneringsmail");
        remindMailRb.setToggleGroup(this.inviteGroup);
        remindMailRb.getStyleClass().add("rbbuttons");
        RadioButton inviteMailRb = new RadioButton("Uitnodigingsmail");
        inviteMailRb.setToggleGroup(this.inviteGroup);
        inviteMailRb.getStyleClass().add("rbbuttons");
        RadioButton thankMailRb = new RadioButton("Bedankmail");
        thankMailRb.setToggleGroup(this.inviteGroup);
        thankMailRb.getStyleClass().add("rbbuttons");
        RadioButton orderReminderRb = new RadioButton("Factuur herinneringsmail");
        orderReminderRb.setToggleGroup(this.inviteGroup);
        orderReminderRb.getStyleClass().add("rbbuttons");
        
        // adding all data
        titleBox.getChildren().addAll(titleLabel, titleTextArea);
        typeBox.getChildren().addAll(remindMailRb, inviteMailRb, thankMailRb, orderReminderRb);
        bodyBox.getChildren().add(htmlEditor);
        contentPane.add(introLabel, 0, 1);
        contentPane.add(titleBox, 0, 2);
        contentPane.add(bodyBox, 0, 3);
        contentPane.add(mailButton, 0, 4);
        contentPane.add(typeLabel, 1, 1);
        contentPane.add(typeBox, 1, 2);

        //Add the contentPane
        getChildren().add(contentPane);
    }

    //getters
    public String getSubject() {
        return this.titleTextArea.getText();
    }

    public String getBody() {
        return this.htmlEditor.getHtmlText();
    }

    public Button getMailButton() {
        return this.mailButton;
    }

    public ToggleGroup getInviteGroup() {
        return inviteGroup;
    }
    //setters
    public void setMail(Mail mail) {
        this.mail = mail;
    }


}
