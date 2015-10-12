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
    private String defaultPath = SettingsController.class.getClassLoader().getResource("templates/").toString();
    private int i;
    public SettingsController(SettingsView settingsView) {
        this.settingsView = settingsView;
        settingsView.changeEmailField.setText(getDefaultMailAdress());
        generateHandler();
        fillStateList();
    }
    /**
     * Add events to the items that need them.
     */
    public void generateHandler(){
    	// Save button handler
    	settingsView.saveButton.setOnAction(e ->{
		validateEmail();
		if(i<1){
			changeMailAdress();
		settingsView.templatesComboBox.setValue("Templates");
		disableTemplateArea();
		for(SettingsState ss : statesList){
			ss.writeToFile();
		}
		refreshTemplateArea();
		}
		else {
			SplashscreenView splashscreenView = new SplashscreenView(title, header, context);
		}
    	});
    	
    	// Reset button handler
    	settingsView.resetButton.setOnAction(e -> {
    		if(settingsView.templatesComboBox.getSelectionModel().getSelectedItem() != "Templates")
    			settingsView.templateArea.setText(settingsState.getDefaultValue());
    	});
    	
    	// Template combobox event handler
    	settingsView.templatesComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {	
				setState();
				settingsView.templateArea.setText(settingsState.getFileString());
			}
    	});
    	
    	settingsView.templateArea.textProperty().addListener(new ChangeListener<String>(){
    		
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!settingsView.templateArea.getText().isEmpty())
					settingsState.updateFileVariable(settingsView.templateArea.getText());
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
    	int typeNumber = settingsView.templatesComboBox.getSelectionModel().getSelectedIndex();
    	settingsState = statesList.get(typeNumber);
    	enableTemplateArea();
    }
    
    public void enableTemplateArea(){
    	settingsView.templateArea.setEditable(true);
    }
    
    public void disableTemplateArea(){
    	settingsView.templateArea.setEditable(false);
    }
    
    public void refreshTemplateArea(){
    	settingsView.templateArea.setText("");
    }
    
    public void changeMailAdress(){
    	// Select file
    	String path = defaultPath + "DEFAULTMAIL.txt";
    	path = path.substring(6, path.length());
    	try {
    		// Create a new PrintWriter
			PrintWriter pw = new PrintWriter(path);
			pw.close(); 
			// New PrintWriter intialize
			pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
			pw.write(settingsView.changeEmailField.getText());
			// Closing the PrintWriter
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 
     * Retrieve the default email from DEFAULTMAIL.txt
     */
    public void validateEmail(){
    	EmailValidator emailValidator = new EmailValidator();
    	SplashDefault settingsSplash = new GeneralSplash();
    	if (!emailValidator.validate(settingsView.changeEmailField.getText().trim())) {
    		settingsSplash = new SplashEmailMessage(settingsSplash);
    		i++;
    	}
    	settingsSplash = new SettingsSetHead(settingsSplash);
    	title = settingsSplash.getTitleText();
    	header = settingsSplash.getHeaderText();
    	context = settingsSplash.getContextText();
    }
    public String getDefaultMailAdress(){
    	String path = defaultPath + "DEFAULTMAIL.txt";
    	path = path.substring(6, path.length());
		String returnMail = "";
		String line = null;
		try {
			// Create a new BufferedReader for DEFAULTMAIL.txt
			BufferedReader br = new BufferedReader(new FileReader(path));
			// Load the email address in the variable "returnMail"
			if ((line = br.readLine()) != null){
		        returnMail = line;
		    }
			// Closing the BufferedReader
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}	
    	return returnMail;
    }
}
