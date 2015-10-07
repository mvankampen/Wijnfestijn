package splashscreens;

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
}
