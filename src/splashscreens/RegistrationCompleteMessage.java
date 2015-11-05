package splashscreens;

public class RegistrationCompleteMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationCompleteMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "De registratie is compleet";
	}
	public String getTitleText() {
		return "Registratie geslaagd!";
	}
	public String getHeaderText() {
		return "De registratie is succesvol voltooid. \n"
				+ "Er is een nieuwe gast toegevoegd!";
	}


}
