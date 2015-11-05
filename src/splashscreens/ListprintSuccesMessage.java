package splashscreens;

public class ListprintSuccesMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public ListprintSuccesMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "De bestellijsten zijn gemaakt, deze kunnen gevonden worden in: \n Gebruikers -> Uw gebruiker -> Wijnfestijn -> Bestellijst -> Huidige datum ( Map) Hier vind u persoonlijke bestellijsten voor alle gasten";
	}
	public String getTitleText() {
		return "Succes";
	}
	public String getHeaderText() {
		return "Bestellijsten zijn gemaakt";
	}


}
