package splashscreens;

public class OrderEmptyMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public OrderEmptyMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Er moet minimaal 1 wijn worden ingevuld!";
	}
	public String getTitleText() {
		return "Lege order!";
	}
	public String getHeaderText() {
		return "Er is geen wijn gekozen!";
	}


}
