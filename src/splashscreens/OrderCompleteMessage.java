package splashscreens;

public class OrderCompleteMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public OrderCompleteMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw order is succesvol aangemaakt in het systeem.";
	}
	public String getTitleText() {
		return "Geslaagde order!";
	}
	public String getHeaderText() {
		return "Order succesvol aangemaakt!";
	}


}
