package splashscreens;

public class CsvIncorrectRowsGuestMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvIncorrectRowsGuestMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return 	  "Deze kolommen wordt verwacht : achternaam, tussenvoegsel, "
				+ "naam, aanhef, straat, straatnummer, zipcode, stad, email, telefoon, comment, referentie.";
		
	}
	public String getTitleText() {
		return "Bestandsfout!";
	}
	public String getHeaderText() {
		return "Het bestand voldoet niet aan de eisen.";
	}


}
