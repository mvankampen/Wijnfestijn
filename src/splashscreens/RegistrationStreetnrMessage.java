package splashscreens;

public class RegistrationStreetnrMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationStreetnrMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Straatnummer kan niet leeg zijn\n";	
	}
}
