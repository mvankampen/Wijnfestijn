package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
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
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}


}
