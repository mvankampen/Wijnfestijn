package splashscreens;

public class CsvImportSucceedMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvImportSucceedMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "De gegevens zijn succesvol ingevoerd.";
	}
	public String getTitleText() {
		return "Import geslaagd!";
	}
	public String getHeaderText() {
		return "Importeren van bestand gelukt!";
	}


}
