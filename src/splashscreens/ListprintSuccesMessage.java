package splashscreens;

/**
 * <p> A wrapper for the decorator adds a succes message for generating orderlists</p>
 *
 * @author Alex van der wal
 */
public class ListprintSuccesMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public ListprintSuccesMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "De bestellijsten zijn gemaakt, deze kunnen gevonden worden in: \n Gebruikers -> Uw gebruiker -> Wijnfestijn -> Bestellijst -> uw opgegeven naam of de datum van vandaag( Map) Hier vind u persoonlijke bestellijsten voor alle gasten";
    }

    public String getTitleText() {
        return "Succes";
    }

    public String getHeaderText() {
        return "Bestellijsten zijn gemaakt";
    }


}
