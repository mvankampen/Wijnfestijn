package splashscreens;

/**
 * Created by Alex on 07-10-15.
 */
public class RegistrationCityMessage extends RegistrationDefaultMessage {
	SplashDefault splashDefault;
	public RegistrationCityMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return splashDefault.getContextText() + "Het stad veld mag niet leeg zijn of nummers bevatten\n";
		
	}
	public String getTitleText() {
		return splashDefault.getTitleText();
	}
	public String getHeaderText() {
		return splashDefault.getHeaderText();
	}


}
