package states;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsStateReminder implements SettingsState{
	private String returnBody, returnTitle, defaultBody = "", defaultTitle = "", pathToFile;
	
	public SettingsStateReminder(){
		fileToVariable();
	}
	
	public void fileToVariable() {
		String line;
		//defaultPath from SettingsState class
		pathToFile = DEFAULTPATH + "REMINDER.html";
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathToFile));
			returnBody = "";
			returnTitle = "";
			
			Pattern pattern = Pattern.compile("<title>(.+?)</title>");
            Matcher matcher = pattern.matcher(readFile(pathToFile));
            matcher.find();
            
			while ((line = br.readLine()) != null){
		        	returnTitle = matcher.group(1);
		        	defaultTitle = matcher.group(1);

	        		returnBody += line + "\n";
					defaultBody += line + "\n";
		    }
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
	
	public void writeToFile(){
		try {
			Pattern pattern = Pattern.compile("<title>(.+?)</title>");
            Matcher matcher = pattern.matcher(readFile(pathToFile));
            matcher.find();
            returnBody = returnBody.replace(matcher.group(1), returnTitle);
			
			// Create a new PrintWriter
			PrintWriter pw = new PrintWriter(pathToFile);
			pw.close();
			// Re-initialize pw
			pw = new PrintWriter(new BufferedWriter(new FileWriter(pathToFile, true)));
			pw.write(returnBody);
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
