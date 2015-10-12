package states;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsStateReminder implements SettingsState{
	private String fileString, defaultString, pathToFile;
	
	public SettingsStateReminder(){
		fileToVariable();
	}
	
	public void fileToVariable() {
		String line;
		//defaultPath from SettingsState class
		pathToFile = defaultPath + "INVITATIONAL.txt";
		pathToFile = pathToFile.substring(6, pathToFile.length());
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathToFile));
			fileString = "";
			while ((line = br.readLine()) != null){
		        fileString += line + "\n";
		    }
			defaultString = fileString;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(){
		try {
			// Create a new PrintWriter
			PrintWriter pw = new PrintWriter(pathToFile);
			pw.close();
			// Re-initialize pw
			pw = new PrintWriter(new BufferedWriter(new FileWriter(pathToFile, true)));
			pw.write(fileString);
			// Closing the PrintWriter
			pw.close();
			defaultString = fileString;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFileVariable(String add){
		fileString = add;
	}

	public String getFileString(){
		return fileString;
	}
	
	public String getDefaultValue(){
		return defaultString;
	}
}
