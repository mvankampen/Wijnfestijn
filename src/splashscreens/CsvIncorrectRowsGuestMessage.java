package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 *
 * @author Alex van der wal on 07-10-15.
 */
public class CsvIncorrectRowsGuestMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public CsvIncorrectRowsGuestMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Deze kolommen wordt verwacht : achternaam, tussenvoegsel, "
            + "naam, aanhef, straat, straatnummer, zipcode, stad, email, telefoon, comment, referentie.";

    }

    public String getTitleText() {
        return "Bestandsfout!";
    }

    public String getHeaderText() {
        return "Het bestand voldoet niet aan de eisen.";
    }


}
