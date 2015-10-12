package states;

public interface SettingsState {
	// Methods
	public void fileToVariable();
	public String getFileString();
	public void writeToFile();
	public void updateFileVariable(String add);
	public String getDefaultValue();
	public String defaultPath = SettingsState.class.getClassLoader().getResource("templates/").toString();
}
