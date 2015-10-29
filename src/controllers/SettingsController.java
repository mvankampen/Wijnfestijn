package controllers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import splashscreens.GeneralSplash;
import splashscreens.SettingsSetHead;
import splashscreens.SplashDefault;
import splashscreens.SplashEmailMessage;
import states.*;
import validators.EmailValidator;
import views.SettingsView;
import views.SplashscreenView;

/**
 * Created by Dennis Sloove on 28-9-2015.
 */
public class SettingsController {
    private SettingsView settingsView;
    private SettingsState settingsState;
    private ArrayList<SettingsState> statesList;
    private String title, header, context;
    private String defaultPath = "src/templates/";
    private int errorCounter = 0;
    private boolean saved = false;
    
    public SettingsController(SettingsView settingsView) {
        this.settingsView = settingsView;
        setMailInfo();
        generateHandler();
        fillStateList();
    }
    /**
     * Add events to the items that need them.
     */
    public void generateHandler(){
    	// Save button handler
    	settingsView.getSaveButton().setOnAction(e ->{
			validateEmail();
			if(errorCounter<1){
				changeMailInfo();
				settingsView.getTemplatesComboBox().setValue("Templates");
				disableTemplateArea();
				for(SettingsState ss : statesList){
					ss.writeToFile();
				}
				saved = true;
				refreshTemplateArea();
				}
			else {
				errorCounter = 0;
				SplashscreenView splashscreenView = new SplashscreenView(title, header, context);
			}
    	});
    	
    	// Reset button handler
    	settingsView.getResetButton().setOnAction(e -> {
    		if(settingsView.getTemplatesComboBox().getSelectionModel().getSelectedItem() != "Templates")
    			settingsView.getTemplateArea().setHtmlText(settingsState.getDefaultBody());
    	});
    	
    	// Template combobox event handler
    	settingsView.getTemplatesComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {	
				setState();
				settingsView.getChangeEmailTitleField().setText(settingsState.getTitle());
				settingsView.getTemplateArea().setHtmlText(settingsState.getBody());
			}
    	});
    	
    	settingsView.getTemplateArea().setOnKeyReleased(new EventHandler<KeyEvent>(){
    		public void handle(KeyEvent event){
    			settingsState.updateBody(settingsView.getTemplateArea().getHtmlText());
				saved = false;
    		}
    	});
    	
    	settingsView.getChangeEmailTitleField().textProperty().addListener(new ChangeListener<String>(){
    		
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!saved){
					settingsState.updateTitle(settingsView.getChangeEmailTitleField().getText());
					saved = false;
				}
			}
    	});
    }
    
    public void fillStateList(){
    	statesList = new ArrayList<SettingsState>();
    	statesList.add(new SettingsStateReminder());
    	statesList.add(new SettingsStateInvitational());
    	statesList.add(new SettingsStateThankYou());
    	statesList.add(new SettingsStateOpenOrder());
    }
    
    public void setState(){    	
    	int typeNumber = settingsView.getTemplatesComboBox().getSelectionModel().getSelectedIndex();
    	settingsState = statesList.get(typeNumber);
    	enableTemplateArea();
    }
    
    public void enableTemplateArea(){
    	settingsView.getTemplateArea().setDisable(false);
    	settingsView.getChangeEmailTitleField().setEditable(true);
    }
    
    public void disableTemplateArea(){
    	settingsView.getTemplateArea().setDisable(true);
    	settingsView.getChangeEmailField().setEditable(false);
    }
    
    public void refreshTemplateArea(){
    	settingsView.getTemplateArea().setHtmlText("");
    	settingsView.getChangeEmailTitleField().setText("");
    }
    
    public void changeMailInfo(){
    	// Select file
    	String path = defaultPath + "DEFAULTMAIL.txt";
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
    
    public void validateEmail(){
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
     * Retrieve the default email from DEFAULTMAIL.txt
     */
    public void setMailInfo(){
    	String path = defaultPath + "DEFAULTMAIL.txt";
  		String line = null;
  		boolean gotName = false;
		try {
			// Create a new BufferedReader for DEFAULTMAIL.txt
			BufferedReader br = new BufferedReader(new FileReader(path));
			// Load the email address in the variable "returnMail"
			while ((line = br.readLine()) != null){
				if(!gotName){
					settingsView.getChangeEmailField().setText(line);
					gotName = true;
				}
				else{
					settingsView.getDefaultMailPasswordField().setText(line);
				}
		    }
			// Closing the BufferedReader
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}	
    }
}
