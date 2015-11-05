package splashscreens;

public class MailEmptyMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public MailEmptyMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Uw title en of body kunnen niet leeg zijn";
	}
	public String getTitleText() {
		return "Lege velden";
	}
	public String getHeaderText() {
		return "Geen mail verstuurd";
	}


}
