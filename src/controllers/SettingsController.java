package controllers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import views.SettingsView;

/**
 * Created by Dennis Sloove on 28-9-2015.
 */
public class SettingsController {
    private SettingsView settingsView;

    public SettingsController(SettingsView settingsView) {
        this.settingsView = settingsView;
        settingsView.changeEmailField.setText(getDefaultMailAdress());
        
        generateHandler();
    }
    /**
     * Add events to the items that need them.
     */
    public void generateHandler(){
    	// Save button handler
    	settingsView.saveButton.setOnAction(e ->{
    		changeMailAdress();
    		settingsView.templatesComboBox.setValue("Templates");
    		refreshTemplateArea();
    		settingsView.templateArea.setEditable(false);
    	});
    	
    	// Template combobox event handler
    	settingsView.templatesComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				fileActions(newValue, oldValue);		
				try {
					String line;
					// Create new BufferedReader
					BufferedReader br = getTemplate(newValue);
					if(br != null){
						// Print all file lines to text area
						while((line = br.readLine())!= null){
							settingsView.templateArea.appendText(line + "\n");
						}
						// Close BufferedReader
						br.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    	});
    }
    /**
     * 
     * @param get newValue from templatesComboBox
     * @param get oldValue from templatesComboBox
     * Executes the methods when templtatesComboBox changes
     */
    public void fileActions(String newValue, String oldValue){
    	// Won't allow editing when no template is selected
    	if(newValue != "Templates")
    		settingsView.templateArea.setEditable(true);
    	writeToTemplateFile(oldValue);
		refreshTemplateArea();
		try {
			getTemplate(newValue);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    /**
     * @param get newValue from templatesComboBox
     * Returns a BufferedReader with a file to read from
     */
    public BufferedReader getTemplate(String newValue) throws FileNotFoundException{
    	BufferedReader br = null;
    	if(newValue != "Templates"){
    		br = new BufferedReader( new FileReader(getRightFileNew(newValue)));
    	}
    	return br;
    }
    /**
     * Clears the template text area
     */
    public void refreshTemplateArea(){
    	settingsView.templateArea.setText("");
    }
    /**
     * @param get oldValue from templatesComboBox
     * Writes the content of templates text area to the correct file
     */
    public void writeToTemplateFile(String oldValue){
    	try {
    		if(oldValue != "Templates"){
    			// Create a new PrintWriter
    			PrintWriter pw = new PrintWriter(getRightFileOld(oldValue));
    			pw.close();
    			// New PrintWriter initialize
    			pw = new PrintWriter(new BufferedWriter(new FileWriter(getRightFileOld(oldValue), true)));
    			pw.write(settingsView.templateArea.getText());
    			// Closing the PrintWriter
    			pw.close();
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * @param get newValue from templatesComboBox
     * Retrieves the file that's selected in the templateComboBox
     */
    public File getRightFileNew(String newValue){
    	String path = "";
    	String fileName = "";
    	File file = null;
    	if(newValue == "Herinnering")
    		fileName = "REMINDER.txt";
    	else if(newValue == "Uitnodiging")
    		fileName = "INVITATIONAL.txt";
    	else if(newValue == "Bedank")
    		fileName = "THANKYOU.txt";
    	else if(newValue == "Factuur herinnering")
    		fileName = "OPENORDER.txt";
    	if(fileName != ""){
    		// Set a path for the file
    		path = SettingsController.class.getClassLoader().getResource("templates/" + fileName).toString();
        	file = new File(path.substring(6, path.length()));
    	}
    	
    	return file;
    }
    /**
     * @param get newValue from templatesComboBox
     * Retrieves the previous file that's selected in the templateComboBox
     */
    public File getRightFileOld(String oldValue){
    	String path = "";
    	String fileName = "";
    	File file = null;
    	if(oldValue == "Herinnering")
    		fileName = "REMINDER.txt";
    	else if(oldValue == "Uitnodiging")
    		fileName = "INVITATIONAL.txt";
    	else if(oldValue == "Bedank")
    		fileName = "THANKYOU.txt";
    	else if(oldValue == "Factuur herinnering")
    		fileName = "OPENORDER.txt";
    	if(fileName != ""){
    		// Set a path for the file
    		path = SettingsController.class.getClassLoader().getResource("templates/" + fileName).toString();
        	file = new File(path.substring(6, path.length()));
    	}
    	return file;
    }
    /**
     * Change the default e-mail address
     */
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
