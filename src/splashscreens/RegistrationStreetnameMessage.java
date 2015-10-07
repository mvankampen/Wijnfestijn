package splashscreens;

public class RegistrationStreetnameMessage extends SplashDefault {
	SplashDefault splashDefault;
	public RegistrationStreetnameMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "de straatnaam invoer is foutief\n";
		
	}
}
