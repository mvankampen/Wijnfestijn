
package splashscreens;

public class SettingsEmailMessage extends SplashDefaultMessage {
	
	SplashDefault splashDefault;
	public SettingsEmailMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	
	public String getContextText() {
		return splashDefault.getContextText() + "E-mail is niet correct\n";
	}

	public String getTitleText() {
		return splashDefault.getTitleText();
	}

	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
