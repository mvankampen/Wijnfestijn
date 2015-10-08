package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class RegistrationSalutationMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationSalutationMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Gelieve uw aanhef te selecteren\n";	
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
