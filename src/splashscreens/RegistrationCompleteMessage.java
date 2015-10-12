package splashscreens;

public class RegistrationCompleteMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationCompleteMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw registratie is compleet";
		
	}
	public String getTitleText() {
		return "Uw registratie is compleet";
	}
	public String getHeaderText() {
		return "Registratie is compleet";
	}


}
