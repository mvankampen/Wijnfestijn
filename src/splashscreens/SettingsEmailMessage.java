package splashscreens;

public class SettingsEmailMessage extends SettingsDefaultMessage {
	
	SplashDefault splashDefault;
	public SettingsEmailMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	
	public String getContextText() {
		return splashDefault.getContextText() + "Email is niet correct\n";
	}

	public String getTitleText() {
		return splashDefault.getTitleText();
	}

	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
