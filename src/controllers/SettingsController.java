package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import services.Util;
import splashscreens.GeneralSplash;
import splashscreens.SettingsSetHead;
import splashscreens.SplashDefault;
import splashscreens.SplashEmailMessage;
import states.*;
import validators.EmailValidator;
import views.SettingsView;
import views.SplashscreenView;

import java.io.*;
import java.util.ArrayList;

/**
 *   <p> Class to handle all the events from the nodes from SettingsView. </p>
 * @author Dennis Sloove.
 * @version 0.1, November 2015
 *       
 */
public class SettingsController {
    private SettingsView settingsView;
    private SettingsState settingsState;
    private ArrayList<SettingsState> statesList;
    private String title, header, context;
    private String defaultPath = "src/templates/";
    private int errorCounter = 0;
    private boolean saved = false;
    private Util util;

    /**
     * Constructor
     *
     * @param settingsView Sets the settingsView
     */
    public SettingsController(SettingsView settingsView) {
        this.settingsView = settingsView;
        this.util = new Util();
        setMailInfo();
        generateHandler();
        fillStateList();

    }

    /**
     * <p>
     * Add events to the items that need them.
     * </p>
     */
    public void generateHandler() {
        // Save button handler
        settingsView.getSaveButton().setOnAction(e -> {
            validateEmail();
            if (errorCounter < 1) {
                changeMailInfo();
                settingsView.getTemplatesComboBox().setValue("Templates");
                disableTemplateArea();
                for (SettingsState ss : statesList) {
                    ss.writeToFile();
                }
                saved = true;
                refreshTemplateArea();
            } else {
                errorCounter = 0;
                SplashscreenView splashscreenView = new SplashscreenView(title, header, context);
            }
        });

        // Reset button handler
        settingsView.getResetButton().setOnAction(e -> {
            if (settingsView.getTemplatesComboBox().getSelectionModel().getSelectedItem()
                != "Templates")
                settingsView.getTemplateArea().setHtmlText(settingsState.getDefaultBody());
        });

        // Template combobox event handler
        settingsView.getTemplatesComboBox().getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<String>() {

                public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                    setState();
                    settingsView.getChangeEmailTitleField().setText(settingsState.getTitle());
                    settingsView.getTemplateArea().setHtmlText(settingsState.getBody());
                }
            });

        settingsView.getTemplateArea().setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                settingsState.updateBody(settingsView.getTemplateArea().getHtmlText());
                saved = false;
            }
        });

        settingsView.getChangeEmailTitleField().textProperty()
            .addListener(new ChangeListener<String>() {

                public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                    if (!saved) {
                        settingsState
                            .updateTitle(settingsView.getChangeEmailTitleField().getText());
                        saved = false;
                    }
                }
            });
    }

    /**
     * <p>
     * Fills an ArrayList with all the States.
     * </p>
     */
    public void fillStateList() {
        statesList = new ArrayList<SettingsState>();
        statesList.add(new SettingsStateReminder());
        statesList.add(new SettingsStateInvitational());
        statesList.add(new SettingsStateThankYou());
        statesList.add(new SettingsStateOpenOrder());
    }

    /**
     * <p>
     * Sets the state.
     * </p>
     */
    public void setState() {
        int typeNumber = settingsView.getTemplatesComboBox().getSelectionModel().getSelectedIndex();
        settingsState = statesList.get(typeNumber);
        enableTemplateArea();
    }

    /**
     * <p>
     * Enables input fields.
     * </p>
     */
    public void enableTemplateArea() {
        settingsView.getTemplateArea().setDisable(false);
        settingsView.getChangeEmailTitleField().setEditable(true);
    }

    /**
     * <p>
     * Disables input fields.
     * </p>
     */
    public void disableTemplateArea() {
        settingsView.getTemplateArea().setDisable(true);
        settingsView.getChangeEmailField().setEditable(false);
    }

    /**
     * <p>
     * Refreshes input fields.
     * </p>
     */
    public void refreshTemplateArea() {
        settingsView.getTemplateArea().setHtmlText("");
        settingsView.getChangeEmailTitleField().setText("");
    }

    /**
     * <p>
     * Change e-mail address.
     * </p>
     */
    public void changeMailInfo() {
        // Select file
        String path = this.util.getTxtFileFromResource("DEFAULTMAIL.txt");
        try {
            // Create a new PrintWriter
            PrintWriter pw = new PrintWriter(path);
            pw.close();
            // New PrintWriter intialize
            pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            pw.write(settingsView.getChangeEmailField().getText() + "\n");
            pw.write(settingsView.getDefaultMailPasswordField().getText());
            // Closing the PrintWriter
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Validates the e-mail address
     * </p>
     */
    public void validateEmail() {
        EmailValidator emailValidator = new EmailValidator();
        SplashDefault settingsSplash = new GeneralSplash();
        if (!emailValidator.validate(settingsView.getChangeEmailField().getText().trim())) {
            settingsSplash = new SplashEmailMessage(settingsSplash);
            errorCounter++;
        }
        settingsSplash = new SettingsSetHead(settingsSplash);
        title = settingsSplash.getTitleText();
        header = settingsSplash.getHeaderText();
        context = settingsSplash.getContextText();
    }

    /**
     * <p>
     * Sets the e-mail address and password.
     * </p>
     */
    public void setMailInfo() {
        String path = this.util.getTxtFileFromResource("DEFAULTMAIL.txt");
        String line = null;
        boolean gotName = false;
        try {
            // Create a new BufferedReader for DEFAULTMAIL.txt
            // Load the email address in the variable "returnMail"
                if (!gotName) {
                    settingsView.getChangeEmailField().setText(line);
                    gotName = true;
                } else {
                    settingsView.getDefaultMailPasswordField().setText(line);
                }
            // Closing the BufferedReader
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
