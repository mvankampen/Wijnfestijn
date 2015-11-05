package splashscreens;

/**
 * @author Alex van der Wal
 * @author Michael van Kampen
 * @version v1.0
 * Description : This is the sub class that is used by the SplashScreens
 * SplashScreens are a "SplashDefault" we can keep wrapping
 * SplashScreens over each other, so that the errors are as Dynamic as possible
 * This is a typical example of the decorator pattern
 */

public class SettingsSplash extends SplashDefault {

		/**
		 * <P>Default Constructor</P>
		 */
	public SettingsSplash(){
		content = "";
		title = "This is a SettingsSplash";
		header = "Uw invoer voldoet niet aan de criteria";
	}
}
