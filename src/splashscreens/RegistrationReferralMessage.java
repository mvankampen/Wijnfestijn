package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
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
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}


}
