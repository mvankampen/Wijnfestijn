package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashSalutationMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public SplashSalutationMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "aanhef niet geselecteerd\n";	
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}

}
