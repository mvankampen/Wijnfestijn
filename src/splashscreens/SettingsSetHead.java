package splashscreens;

public class SettingsSetHead extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public SettingsSetHead(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "";
		
	}
	public String getTitleText() {
		return "Error screen";
	}
	public String getHeaderText() {
		return "Controleer de mail invoer alstublieft";
	}


}
