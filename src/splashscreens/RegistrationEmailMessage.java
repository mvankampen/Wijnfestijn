package splashscreens;
/**
 * Created by Alex on 07-10-15.
 */
public class RegistrationEmailMessage extends RegistrationDefaultMessage {
			SplashDefault splashDefault;
			public RegistrationEmailMessage(SplashDefault splashDefault)
			{
				this.splashDefault = splashDefault;
			}
			@Override
			public String getContextText() {
				return splashDefault.getContextText() + "de email invoer is foutief\n";
				
			}
			public String getTitleText() {
				return splashDefault.getTitleText();
			}
			public String getHeaderText() {
				return splashDefault.getHeaderText();
			}



}
