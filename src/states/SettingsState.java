package states;

public interface SettingsState {
	// Methods
	public void fileToVariable();
	public String getBody();
	public String getTitle();
	public void writeToFile();
	public void updateBody(String add);
	public void updateTitle(String add);
	public String getDefaultBody();
	public String getDefaultTitle();
	public String defaultPath = "src/templates/";
}
