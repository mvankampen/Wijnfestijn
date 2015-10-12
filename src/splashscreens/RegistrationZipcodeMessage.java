package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class RegistrationZipcodeMessage extends SplashDefault {
	SplashDefault splashDefault;
	public RegistrationZipcodeMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "de postcode invoer is foutief\n";
		
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}


}
