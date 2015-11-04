package splashscreens;

public class CsvIncorrectRowsWineMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvIncorrectRowsWineMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw CSV data kon niet worden ingelezen omdat de rijen niet overeenkwamen met wat het systeem verwacht /n"
				+ "Deze indeling wordt verwacht : naam, uitgever, jaar, prijs, rank, categorie, type, inkoopprijs, margin" +
				"Kijk uw CSV bestand na en probeer het nogmaals";
		
	}
	public String getTitleText() {
		return "Er is iets fout gegaan";
	}
	public String getHeaderText() {
		return "Foute rijen indeling CSV";
	}


}
