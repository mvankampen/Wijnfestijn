package splashscreens;
/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 * @author Alex van der wal on 07-10-15.
 */
public class AdjustSetHead extends SplashDefaultMessage {
	SplashDefault splashDefault;
	/**
	 * @param splashDefault so that it can wrap itself around the object given
	 */
	public AdjustSetHead(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "";
		
	}
	public String getTitleText() {
		return "Onjuiste invoer!";
	}
	public String getHeaderText() {
		return "De ingevoerde gegevens zijn onjuist. Kijk hieronder voor meer details";
	}
}
