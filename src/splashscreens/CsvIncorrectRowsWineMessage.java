package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 *
 * @author Alex van der wal on 07-10-15.
 */
public class CsvIncorrectRowsWineMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public CsvIncorrectRowsWineMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return
            "Uw CSV data kon niet worden ingelezen omdat de rijen niet overeenkwamen met wat het systeem verwacht /n"
                + "Deze indeling wordt verwacht : naam, uitgever, jaar, prijs, rank, categorie, type, inkoopprijs, margin"
                +
                "Kijk uw CSV bestand na en probeer het nogmaals";

    }

    public String getTitleText() {
        return "Bestandsfout!";
    }

    public String getHeaderText() {
        return "Het bestand voldoet niet aan de eisen.";
    }


}
