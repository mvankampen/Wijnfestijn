package splashscreens;

/**
 * <p> A wrapper for the decorator gives a succes message for importing</p>
 *
 * @author Alex van der wal
 */
public class ImportSucceedMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public ImportSucceedMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Er zijn geen fouten gevonden tijdens het importeren van de gegevens.";
    }

    public String getTitleText() {
        return "Import geslaagd!";
    }

    public String getHeaderText() {
        return "De gegevens zijn correct geïmporteerd.";
    }


}
