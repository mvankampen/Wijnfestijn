package splashscreens;

public class AdjustSetHead extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public AdjustSetHead(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "";
		
	}
	public String getTitleText() {
		return "Onjuiste invoer!";
	}
	public String getHeaderText() {
		return "De ingevoerde gegevens zijn onjuist. Kijk hieronder voor meer details";
	}
}
