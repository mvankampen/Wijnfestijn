package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashReferralMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public SplashReferralMessage(SplashDefault splashDefault)
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
