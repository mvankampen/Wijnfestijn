package splashscreens;

public class MailSendMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public MailSendMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw mail is verstuurd naar de gewenste doelgroep";
	}
	public String getTitleText() {
		return "Succesvolle verzending";
	}
	public String getHeaderText() {
		return "opdracht voltooid";
	}


}
