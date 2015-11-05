package splashscreens;

public class DebtorsEmptyMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public DebtorsEmptyMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "Er zijn geen debiteuren aanwezig";
		
	}
	public String getTitleText() {
		return "Geen debiteuren";
	}
	public String getHeaderText() {
		return "Lege data";
	}


}
