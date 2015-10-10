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
import states.*;
import views.SettingsView;

/**
 * Created by Dennis Sloove on 28-9-2015.
 */
public class SettingsController {
    private SettingsView settingsView;
    private SettingsState settingsState;
    private ArrayList<SettingsState> statesList;

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
		changeMailAdress();
		settingsView.templatesComboBox.setValue("Templates");
		disableTemplateArea();
		for(SettingsState ss : statesList){
			ss.writeToFile();
		}
		refreshTemplateArea();
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
    	String path = SettingsController.class.getClassLoader().getResource("templates/DEFAULTMAIL.txt").toString();
    	try {
    		// Create a new PrintWriter
			PrintWriter pw = new PrintWriter(path.substring(6, path.length()));
			pw.close(); 
			// New PrintWriter intialize
			pw = new PrintWriter(new BufferedWriter(new FileWriter(path.substring(6, path.length()), true)));
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
    public String getDefaultMailAdress(){
    	String path = SettingsController.class.getClassLoader().getResource("templates/DEFAULTMAIL.txt").toString();
		String returnMail = "";
		String line = null;
		try {
			// Create a new BufferedReader for DEFAULTMAIL.txt
			BufferedReader br = new BufferedReader(new FileReader(path.substring(6, path.length())));
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
