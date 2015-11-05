package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashStreetnameMessage extends SplashDefault {
	SplashDefault splashDefault;
	public SplashStreetnameMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Foutieve straatnaam\n";
		
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
