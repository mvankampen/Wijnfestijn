package splashscreens;

public class CsvIncorrectFileMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvIncorrectFileMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Er is geen .CSV bestand geselecteerd.";
	}
	public String getTitleText() {
		return "Bestandsfout!";
	}
	public String getHeaderText() {
		return "Verkeerd bestand!";
	}


}
