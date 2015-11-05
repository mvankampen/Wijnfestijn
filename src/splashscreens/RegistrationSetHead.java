package splashscreens;
/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 * @author Alex van der wal
 */
public class RegistrationSetHead extends SplashDefaultMessage {
	SplashDefault splashDefault;
	/**
	 * @param splashDefault so that it can wrap itself around the object given
	 */
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
