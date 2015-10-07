package splashscreens;

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


}
