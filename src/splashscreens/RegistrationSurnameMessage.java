package splashscreens;

public class RegistrationSurnameMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationSurnameMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "de achternaam invoer is foutief\n";
		
	}

}
