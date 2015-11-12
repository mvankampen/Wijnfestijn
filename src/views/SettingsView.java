package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;


/**
 * <p> Here the user can change the settings</p>
 * @author Dennis Sloove.
 * @version 0.1, November 2015
 *        
 */
public class SettingsView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    public TextField changeEmailField, changeEmailTitleField;
    public ComboBox<String> templatesComboBox;
    public HTMLEditor templateArea;
    public Button saveButton, resetButton;
    public PasswordField defaultMailPasswordField;

    /**
     * <p>
     * Used for registering itself in the hashMap of the ScreensController
     * to enable navigation
     * </p>
     */
    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    /**
     * Constructor
     */
    public SettingsView() {
        createView();
    }

    /**
     * <p>
     * Adds the style class and sets the fixed height to the screen
     * </p>
     */
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    /**
     * <p>
     * Create controls.
     * </p>
     */
    public void setUpContentPane() {
        ObservableList<String> options = FXCollections
            .observableArrayList("Herinnering", "Uitnodiging", "Bedank", "Factuur herinnering");

        //Create items
        changeEmailField = new TextField();
        templatesComboBox = new ComboBox<String>(options);
        templatesComboBox.setValue("Templates");
        templateArea = new HTMLEditor();
        templateArea.setDisable(true);
        templateArea.setMaxWidth(600);
        templateArea.setMaxHeight(300);
        saveButton = new Button("Opslaan");
        saveButton.getStyleClass().add("form_buttons");
        resetButton = new Button("Reset deze template");
        resetButton.getStyleClass().add("form_buttons");
        defaultMailPasswordField = new PasswordField();
        changeEmailTitleField = new TextField();
        changeEmailTitleField.setMaxWidth(450);

        ScrollPane sp = new ScrollPane();
        VBox contentVBox = new VBox(25);

        HBox emailHBox = new HBox(20);
        emailHBox.getChildren().addAll(changeEmailField);

        HBox passwordHBox = new HBox(20);
        passwordHBox.getChildren().addAll(defaultMailPasswordField);

        VBox templatesVBox = new VBox(20);
        templatesVBox.getChildren()
            .addAll(templatesComboBox, new Label("Wijzig het mail onderwerp: "),
                changeEmailTitleField, new Label("Wijzig de mail body: "), templateArea,
                resetButton);

        HBox saveButtonBox = new HBox(100);
        saveButtonBox.getChildren().addAll(saveButton);
        saveButtonBox.setAlignment(Pos.CENTER);
        HBox.setMargin(saveButton, new Insets(0, 0, 0, 105));

        Label emailLabel = new Label("E-mail settings");
        emailLabel.getStyleClass().add("settings_title");

        Label templateLabel = new Label("Template settings");
        templateLabel.getStyleClass().add("settings_title");

        contentVBox.getChildren()
            .addAll(emailLabel, new Label("Wijzig het standaard mail adres: "), emailHBox,
                new Label("Wijzig uw mail wachtwoord: "), passwordHBox, templateLabel,
                templatesVBox);
        contentVBox.getStyleClass().add("settings_white");
        contentVBox.setPrefWidth(800);

        sp.setContent(contentVBox);
        sp.setPrefHeight(500);
        sp.setPrefWidth(800);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setPadding(new Insets(25));
        sp.getStyleClass().add("settings_white");

        //Create a BorderPane
        BorderPane bPane = new BorderPane();
        bPane.setCenter(sp);
        bPane.setRight(saveButtonBox);
        bPane.setLayoutX(100);
        bPane.setLayoutY(200);

        getChildren().addAll(bPane);
    }

    // ***** GETTERS *****

    /**
     * @return Returns change e-mail field
     */
    public TextField getChangeEmailField() {
        return changeEmailField;
    }

    /**
     * @return Returns change e-mail title field
     */
    public TextField getChangeEmailTitleField() {
        return changeEmailTitleField;
    }

    /**
     * @return Returns password field
     */
    public PasswordField getDefaultMailPasswordField() {
        return defaultMailPasswordField;
    }

    /**
     * @return Returns reset button
     */
    public Button getResetButton() {
        return resetButton;
    }

    /**
     * @return Returns save button
     */
    public Button getSaveButton() {
        return saveButton;
    }

    /**
     * @return Returns templates box
     */
    public ComboBox<String> getTemplatesComboBox() {
        return templatesComboBox;
    }

    /**
     * @return Returns template area
     */
    public HTMLEditor getTemplateArea() {
        return templateArea;
    }
}
