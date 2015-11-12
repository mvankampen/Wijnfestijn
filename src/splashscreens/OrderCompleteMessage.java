package splashscreens;

/**
 * <p> A wrapper for the decorator gives a message that the order went through </p>
 *
 * @author Alex van der wal
 */
public class OrderCompleteMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public OrderCompleteMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Uw order is succesvol aangemaakt in het systeem.";
    }

    public String getTitleText() {
        return "Geslaagde order!";
    }

    public String getHeaderText() {
        return "Order succesvol aangemaakt!";
    }


}
