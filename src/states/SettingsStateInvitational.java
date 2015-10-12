package states;

import java.io.*;

public class SettingsStateInvitational implements SettingsState{
	private String returnBody, returnTitle, defaultBody = "", defaultTitle = "", pathToFile;
	
	public SettingsStateInvitational(){
		fileToVariable();
	}
	
	public void fileToVariable() {
		String line;
		//defaultPath from SettingsState class
		pathToFile = defaultPath + "INVITATIONAL.txt";
		try {
			boolean gotSubject = false;
			boolean emptyFirst = false;
			BufferedReader br = new BufferedReader(new FileReader(pathToFile));
			returnBody = "";
			returnTitle = "";
			while ((line = br.readLine()) != null){
		        if(!gotSubject){
		        	returnTitle = line;
		        	defaultTitle = line;
		        	gotSubject = true;
		        }
		        else{
		        	if(!emptyFirst){
		        		emptyFirst = true;
		        	}
		        	else{
		        		returnBody += line + "\n";
						defaultBody += line + "\n";
		        	}
				}
		    }
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
			pw.write(returnTitle + "\n\n" + returnBody);
			// Closing the PrintWriter
			pw.close();
			defaultBody = returnBody;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBody(String add){
		returnBody = add;
	}
	
	public void updateTitle(String add){
		returnTitle = add;
	}

	public String getBody(){
		return returnBody;
	}
	
	public String getTitle(){
		return returnTitle;
	}
	
	public String getDefaultBody(){
		return defaultBody;
	}
	
	public String getDefaultTitle(){
		return defaultTitle;
	}
}
