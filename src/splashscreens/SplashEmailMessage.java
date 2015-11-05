package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class SplashEmailMessage extends SplashDefaultMessage {
			SplashDefault splashDefault;
			public SplashEmailMessage(SplashDefault splashDefault)
			{
				this.splashDefault = splashDefault;
			}
			@Override
			public String getContextText() {
				return splashDefault.getContextText() + "foutieve e-mail\n";
				
			}
			public String getTitleText() {
				return splashDefault.getTitleText();
			}
			public String getHeaderText() {
				return splashDefault.getHeaderText();
			}



}
