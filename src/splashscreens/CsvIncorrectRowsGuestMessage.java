package splashscreens;

public class CsvIncorrectRowsGuestMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvIncorrectRowsGuestMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw CSV data kon niet worden ingelezen omdat de rijen niet overeenkwamen met wat het systeem verwacht /n"
				+ "Deze indeling wordt verwacht : achternaam tussenvoegsel"
				+ "naam aanhef straat straatnummer zipcode stad email telefoon comment referentie";
		
	}
	public String getTitleText() {
		return "Er is iets fout gegaan";
	}
	public String getHeaderText() {
		return "Foute rijen indeling CSV";
	}


}
