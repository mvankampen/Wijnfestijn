package splashscreens;

public class ImportSucceedMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public ImportSucceedMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "data import succesvol";
		
	}
	public String getTitleText() {
		return "Succes";
	}
	public String getHeaderText() {
		return "Geen fouten gevonden";
	}


}
