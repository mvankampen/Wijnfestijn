package splashscreens;

public class OrderDuplicateMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public OrderDuplicateMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "U kunt niet 2 orders voor 1 wijn maken";
		
	}
	public String getTitleText() {
		return "Orderline fout";
	}
	public String getHeaderText() {
		return "Er is al een orderline voor deze wijn";
	}


}