package splashscreens;

public class OrderEmptyMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public OrderEmptyMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Je moet minimaal 1 orderline hebben";
		
	}
	public String getTitleText() {
		return "Lege order";
	}
	public String getHeaderText() {
		return "Lege order";
	}


}
