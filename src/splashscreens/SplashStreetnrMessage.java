package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashStreetnrMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public SplashStreetnrMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Straatnummer kan niet leeg zijn\n";	
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
