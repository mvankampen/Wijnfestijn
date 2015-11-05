package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashFirstnameMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public SplashFirstnameMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "foutieve voornaam\n";
		
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}


}
