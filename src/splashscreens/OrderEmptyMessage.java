package splashscreens;

/**
 * <p> A wrapper for the decorator gives a message that there are no orderlines in the order</p>
 *
 * @author Alex van der wal
 */
public class OrderEmptyMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public OrderEmptyMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Er moet minimaal 1 wijn worden ingevuld!";
    }

    public String getTitleText() {
        return "Lege order!";
    }

    public String getHeaderText() {
        return "Er is geen wijn gekozen!";
    }


}
