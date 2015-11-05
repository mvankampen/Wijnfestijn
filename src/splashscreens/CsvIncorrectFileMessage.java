package splashscreens;

public class CsvIncorrectFileMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvIncorrectFileMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Het geselecteerde bestand is geen .CSV bestand.";
	}
	public String getTitleText() {
		return "Bestandsfout!";
	}
	public String getHeaderText() {
		return "Verkeerd bestandstype geselecteerd!";
	}


}
