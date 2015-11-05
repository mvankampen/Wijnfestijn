package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 *
 * @author Alex van der wal on 07-10-15.
 */
public class CsvImportSucceedMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public CsvImportSucceedMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "De gegevens zijn succesvol ingevoerd.";
    }

    public String getTitleText() {
        return "Import geslaagd!";
    }

    public String getHeaderText() {
        return "Importeren van bestand gelukt!";
    }


}
