package splashscreens;
public class RegistrationReferralMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationReferralMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Selecteer uw referentie\n";
		
	}

}
