package views;

import controllers.ScreensController;
import enums.MailType;
import interfaces.ControlledScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import models.Mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MailView extends AnchorPane implements ControlledScreen {

    private ScreensController screensController;
    @FXML
    private TextField titleTextArea;
    @FXML
    private Button mailButton = new Button("Verzenden");
    @FXML
    private HTMLEditor htmlEditor;

    private ToggleGroup inviteGroup = new ToggleGroup();
    private Mail mail;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public MailView(Mail mail) {
        this.mail = mail;
        createView();
    }

    public void updateFields() {
        this.titleTextArea.setText(this.mail.getSubject());
        System.out.println(this.mail.getBody());
        this.htmlEditor.setHtmlText(this.mail.getBody());
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    public void setUpContentPane() {
        //contentPane details
        GridPane contentPane = new GridPane();
        contentPane.setLayoutX(100);
        contentPane.setLayoutY(200);
        contentPane.setVgap(10);
        contentPane.setHgap(25);

        //Intro label
        String introText = "Hier kunt u de mails opstellen. Kies welke mail u wilt"
                + " opstellen en voer\nde titel en body in. Klik op verzenden en de applicatie"
                + " doet de rest";
        Label introLabel = new Label(introText);

        //Create VBoxes row 1
        VBox titleBox = new VBox();
        VBox bodyBox = new VBox();

        //Create VBoxes row 2
        VBox typeBox = new VBox();

        this.mailButton = new Button("Verzenden");

        //Creating items row 1
        Label titleLabel = new Label("Vul hier de titel in:");
        this.titleTextArea = new TextField();
        this.htmlEditor = new HTMLEditor();
        this.htmlEditor.setMaxHeight(300);

        //Creating items row 2
        Label typeLabel = new Label("Selecteer wat voor een soort\nmail u wilt verzenden");
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
        this.mailButton = new Button("Verzenden");
        mailButton.getStyleClass().add("form_buttons");


        //Adding to VBoxes
        titleBox.getChildren().addAll(titleLabel, titleTextArea);
        typeBox.getChildren().addAll(remindMailRb, inviteMailRb, thankMailRb, orderReminderRb);
        bodyBox.getChildren().add(htmlEditor);

        //Adding items row 1
        contentPane.add(introLabel, 0, 1);
        contentPane.add(titleBox, 0, 2);
        contentPane.add(bodyBox, 0, 3);
        contentPane.add(mailButton, 0, 4);
        contentPane.add(typeLabel, 1, 1);
        contentPane.add(typeBox, 1, 2);

        //Add the contentPane
        getChildren().add(contentPane);
    }

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
    public void setMail(Mail mail) {
        this.mail = mail;
    }


}
