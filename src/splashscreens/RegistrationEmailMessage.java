package splashscreens;

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


}
