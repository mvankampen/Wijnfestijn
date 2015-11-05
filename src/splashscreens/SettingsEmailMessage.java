
package splashscreens;
/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 * @author Alex van der wal
 */
public class SettingsEmailMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	/**
	 * @param splashDefault so that it can wrap itself around the object given
	 */
	public SettingsEmailMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	
	public String getContextText() {
		return splashDefault.getContextText() + "E-mail is niet correct\n";
	}

	public String getTitleText() {
		return splashDefault.getTitleText();
	}

	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
