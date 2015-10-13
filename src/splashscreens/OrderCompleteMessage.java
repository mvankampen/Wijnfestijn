package splashscreens;

public class OrderCompleteMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public OrderCompleteMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw order is compleet";
		
	}
	public String getTitleText() {
		return "Compleet";
	}
	public String getHeaderText() {
		return "Uw order is door gevoerd naar het systeem";
	}


}
