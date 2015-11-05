package splashscreens;

/**
 * <p> A wrapper for the decorator gives a error that the file is not a CSV file</p>
 *
 * @author Alex van der wal
 */
public class CsvIncorrectFileMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public CsvIncorrectFileMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Er is geen .CSV bestand geselecteerd.";
    }

    public String getTitleText() {
        return "Bestandsfout!";
    }

    public String getHeaderText() {
        return "Verkeerd bestand!";
    }


}
