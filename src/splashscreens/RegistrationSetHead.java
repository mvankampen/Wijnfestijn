package splashscreens;

public class RegistrationSetHead extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationSetHead(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "";
		
	}
	public String getTitleText() {
		return "U heeft een fout gemaakt in uw registratie formulier";
	}
	public String getHeaderText() {
		return "Controleer uw invoer alstublieft";
	}


}
