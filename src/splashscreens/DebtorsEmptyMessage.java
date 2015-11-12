package splashscreens;

/**
 * <p> A wrapper for the decorator gives a error that there are no debtors</p>
 *
 * @author Alex van der wal
 */
public class DebtorsEmptyMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public DebtorsEmptyMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Er zijn geen debiteuren aanwezig";
    }

    public String getTitleText() {
        return "Geen debiteuren gevonden!";
    }

    public String getHeaderText() {
        return "";
    }


}
