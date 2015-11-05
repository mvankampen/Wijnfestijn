package splashscreens;

public class ImportSucceedMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public ImportSucceedMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Er zijn geen fouten gevonden tijdens het importeren van de gegevens.";
	}
	public String getTitleText() {
		return "Import geslaagd!";
	}
	public String getHeaderText() {
		return "De gegevens zijn correct geïmporteerd.";
	}


}
