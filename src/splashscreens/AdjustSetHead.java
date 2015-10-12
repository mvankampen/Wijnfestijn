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
		return "Foute invoer!";
	}
	public String getHeaderText() {
		return "Uw invoer is foutief, hieronder welke invoeren fout zijn";
	}


}
