package views;

import controllers.ScreensController;
import enums.MailType;
import interfaces.ControlledScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MailView extends AnchorPane implements ControlledScreen {

    private ScreensController screensController;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    private TextField titleTextArea;
    private TextArea bodyTextArea;
    private final ToggleGroup inviteGroup = new ToggleGroup();
    private Button mailButton = new Button("Verzenden");
    private MailType mailType;

    public MailView() {
        createView();
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    public void setUpContentPane() {
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
        String orderReminderRbText = "Factuur herinneringsmail";
        //Creating items row 1
        Label titleLabel = new Label(titleText);
        Label bodyLabel = new Label(bodyText);
        this.titleTextArea = new TextField();
        this.bodyTextArea = new TextArea();
        Label selectionLabel = new Label(selectionText);

        //Creating items row 2
        Label typeLabel = new Label(typeText);
        RadioButton remindMailRb = new RadioButton(remindMailRbText);
        remindMailRb.setToggleGroup(this.inviteGroup);
        remindMailRb.getStyleClass().add("rbbuttons");
        RadioButton inviteMailRb = new RadioButton(inviteMailRbText);
        inviteMailRb.setToggleGroup(this.inviteGroup);
        inviteMailRb.getStyleClass().add("rbbuttons");
        RadioButton thankMailRb = new RadioButton(thankMailRbText);
        thankMailRb.setToggleGroup(this.inviteGroup);
        thankMailRb.getStyleClass().add("rbbuttons");
        RadioButton orderReminderRb = new RadioButton(orderReminderRbText);
        orderReminderRb.setToggleGroup(this.inviteGroup);
        orderReminderRb.getStyleClass().add("rbbuttons");
        this.mailButton = new Button("Verzenden");
        mailButton.getStyleClass().add("form_buttons");

        this.inviteGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (inviteGroup.getSelectedToggle() != null) {
                    mailType = MailType.values()[inviteGroup.getToggles().indexOf(newValue)];
                    System.out.println(mailType.toString());

                }
            }
        });

        //Adding to VBoxes
        titleBox.getChildren().addAll(titleLabel, titleTextArea);
        bodyBox.getChildren().addAll(bodyLabel, bodyTextArea);
        typeBox.getChildren().addAll(remindMailRb, inviteMailRb, thankMailRb, orderReminderRb);

        //Adding items row 1
        contentPane.add(introLabel, 0, 1);
        contentPane.add(titleBox, 0, 2);
        contentPane.add(bodyBox, 0, 3);
        contentPane.add(typeLabel, 1, 1);
        contentPane.add(typeBox, 1, 2);
        contentPane.add(selectionLabel, 1, 3);
        contentPane.add(mailButton, 1, 4);

        //Add the contentPane
        getChildren().add(contentPane);
    }

    public String getSubject() {
        return this.titleTextArea.getText();
    }

    public String getBody() {
        return this.bodyTextArea.getText();
    }

    public MailType getMailOption() {
        return this.mailType;
    }

    public Button getMailButton() {
        return this.mailButton;
    }
}