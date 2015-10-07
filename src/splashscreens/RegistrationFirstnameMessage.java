package splashscreens;

public class RegistrationFirstnameMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationFirstnameMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "de voornaam invoer is foutief\n";
		
	}

}
