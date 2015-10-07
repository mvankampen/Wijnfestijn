package splashscreens;

public class RegistrationStreernrMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationStreernrMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Straatnummer kan niet leeg zijn\n";	
	}
}
