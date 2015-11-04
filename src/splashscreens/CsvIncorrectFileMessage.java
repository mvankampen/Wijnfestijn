package splashscreens;

public class CsvIncorrectFileMessage extends SplashDefaultMessage {
	SplashDefault splashDefault;
	public CsvIncorrectFileMessage(SplashDefault splashDefault)
	{
		this.splashDefault = splashDefault;
	}
	@Override
	public String getContextText() {
		return "De file die je probeert te uploaden is geen CSV bestand";
		
	}
	public String getTitleText() {
		return "Er is iets fout gegaan";
	}
	public String getHeaderText() {
		return "Fout filetype";
	}


}
