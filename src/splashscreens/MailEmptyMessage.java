package splashscreens;
/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 * @author Alex van der wal on 07-10-15.
 */
public class MailEmptyMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	/**
	 * @param splashDefault so that it can wrap itself around the object given
	 */
	public MailEmptyMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw title en of body kunnen niet leeg zijn";
	}
	public String getTitleText() {
		return "Lege velden";
	}
	public String getHeaderText() {
		return "Geen mail verstuurd";
	}


}
