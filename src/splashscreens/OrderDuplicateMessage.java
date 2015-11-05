package splashscreens;

/**
 * <p> A wrapper for the decorator gives a message that the orderline already exists</p>
 *
 * @author Alex van der wal on 07-10-15.
 */
public class OrderDuplicateMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public OrderDuplicateMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "U kunt niet 2 keer dezelfde wijn invoeren!";
    }

    public String getTitleText() {
        return "Dubbele wijn";
    }

    public String getHeaderText() {
        return "Deze wijn is al gekozen.";
    }


}
