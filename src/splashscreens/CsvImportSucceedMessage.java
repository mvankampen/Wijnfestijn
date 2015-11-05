package splashscreens;

/**
 * <p> A wrapper for the decorator, Gives a succes message for CSV import </p>
 *
 * @author Alex van der wal
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
