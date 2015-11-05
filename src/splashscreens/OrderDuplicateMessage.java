package splashscreens;

public class OrderDuplicateMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public OrderDuplicateMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "U kunt niet 2 keer dezelfde wijn invoeren!";
	}
	public String getTitleText() {
		return "Dubbele wijn";
	}
	public String getHeaderText() {
		return "Deze wijn is al gekozen.";
	}


}