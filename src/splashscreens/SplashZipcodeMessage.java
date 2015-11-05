package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashZipcodeMessage extends SplashDefault {
	SplashDefault splashDefault;
	public SplashZipcodeMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "foutieve postcode\n";
		
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}


}
