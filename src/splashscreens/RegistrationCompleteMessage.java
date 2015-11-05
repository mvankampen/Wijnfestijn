package splashscreens;
/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 * @author Alex van der wal
 */
public class RegistrationCompleteMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	/**
	 * @param splashDefault so that it can wrap itself around the object given
	 */
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
