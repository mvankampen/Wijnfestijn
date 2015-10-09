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
 * Created by Sander de Jong on 28-9-2015.
 */
public class SettingsController {
    private SettingsView settingsView;
    private FileReader fr;
    private File file;
    BufferedReader br;
    PrintWriter pw;
    String oldValue2, newValue2;

    public SettingsController(SettingsView settingsView) {
        this.settingsView = settingsView;
        generateHandler();
    }
    
    public void generateHandler(){
    	//Save button handler
    	settingsView.saveButton.setOnAction(e ->{
    		saveLastEditedTemplate();
    	});
    	
    	//template combobox event handler
    	settingsView.templatesComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				oldValue2 = oldValue;
				newValue2 = newValue;
				switch(newValue){
					case "Herinnering":
						try {
							writeToFile();
							refreshTemplateArea();
							getTemplate();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case "Uitnodiging":
						try{
							writeToFile();
							refreshTemplateArea();
							getTemplate();
						} catch(FileNotFoundException e){
							e.printStackTrace();
						}
						break;
					case "Bedank":
						try{
							writeToFile();
							refreshTemplateArea();
							getTemplate();
						} catch(FileNotFoundException e){
							e.printStackTrace();
						}
						break;
					case "Factuur herinnering":
						try{
							writeToFile();
							refreshTemplateArea();
							getTemplate();
						} catch(FileNotFoundException e){
							e.printStackTrace();
						}
						break;
						
				}
				try {
					String line;
					while((line = br.readLine())!= null){
						settingsView.templateArea.appendText(line + "\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    	});
    }
    
    public void getTemplate() throws FileNotFoundException{
    	fr = new FileReader(getRightFileNew(newValue2));
    	br = new BufferedReader(fr);
    }
    
    public void refreshTemplateArea(){
    	settingsView.templateArea.setText("");
    }
    
    public void writeToFile(){
    	try {
    		if(oldValue2 != "Templates"){
    			pw = new PrintWriter(getRightFileOld(oldValue2));
    			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(getRightFileOld(oldValue2), true)));
    			pw.write(settingsView.templateArea.getText());
    			pw.close();
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void saveLastEditedTemplate(){
    	try {
    		if(newValue2 != "Templates"){
    			pw = new PrintWriter(getRightFileNew(newValue2));
    			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(getRightFileNew(newValue2), true)));
    			pw.write(settingsView.templateArea.getText());
    			pw.close();
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public File getRightFileNew(String newValue){
    	String path = "";
    	switch(newValue){
    	case "Herinnering":
    		path = SettingsController.class.getClassLoader().getResource("templates/REMINDER.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	case "Uitnodiging":
    		path = SettingsController.class.getClassLoader().getResource("templates/INVITATIONAL.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	case "Bedank":
    		path = SettingsController.class.getClassLoader().getResource("templates/THANKYOU.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	case "Factuur herinnering":
    		path = SettingsController.class.getClassLoader().getResource("templates/OPENORDER.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	}
    	 return file;
    }
    
    public File getRightFileOld(String oldValue){
    	String path = "";
    	switch(oldValue){
    	case "Herinnering":
    		path = SettingsController.class.getClassLoader().getResource("templates/REMINDER.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	case "Uitnodiging":
    		path = SettingsController.class.getClassLoader().getResource("templates/INVITATIONAL.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	case "Bedank":
    		path = SettingsController.class.getClassLoader().getResource("templates/THANKYOU.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	case "Factuur herinnering":
    		path = SettingsController.class.getClassLoader().getResource("templates/OPENORDER.txt").toString();
        	path = path.substring(6, path.length());
        	file = new File(path.toString());
    		break;
    	}
    	 return file;
    }
}
