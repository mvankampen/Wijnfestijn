package splashscreens;

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

}
