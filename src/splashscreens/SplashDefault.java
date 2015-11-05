package splashscreens;
/**
 * @author Alex on 07-10-15.
 * @version v1.0
 * Description : This is the super class that is used by all SplashScreens
 * Because all SplashScreens are a "SplashDefault" we can keep wrapping
 * SplashScreens over each other, so that the errors are as Dynamic as possible
 * This is a typical example of the decorator pattern
 */
public class SplashDefault {
	
	String content = "This isn't a text, just a test";
	String title = "This is a test";
	String header = "This is also a test";
	/**
	 * 
	 * @return the content set by the decorator, will be shown in the SplashScreenView
	 */
	public String getContextText() {
		return content;	
	}
	/**
	 * 
	 * @return the title set by the decorator, will be shown in the SplashScreenView
	 */
	public String getTitleText() {
		return title;
	}
	/**
	 * 
	 * @return the header set by the decorator, will be shown in the SplashScreenView
 	 */
	public String getHeaderText() {
		return header;
	}
}
