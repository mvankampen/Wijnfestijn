package states;

import java.io.IOException;

public interface SettingsState {
	// Methods
	public void fileToVariable();
	public String readFile(String fileName) throws IOException;
	public String getBody();
	public String getTitle();
	public void writeToFile();
	public void updateBody(String add);
	public void updateTitle(String add);
	public String getDefaultBody();
	public String getDefaultTitle();
	public final String DEFAULTPATH = "src/templates/";
}
