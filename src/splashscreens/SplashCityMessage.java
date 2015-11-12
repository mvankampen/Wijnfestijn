package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 *
 * @author Alex van der wal on 07-10-15.
 */
public class SplashCityMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public SplashCityMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    public String getContextText() {
        return splashDefault.getContextText()
            + "Het stad veld mag niet leeg zijn of nummers bevatten\n";
    }

    public String getTitleText() {
        return splashDefault.getTitleText();
    }

    public String getHeaderText() {
        return splashDefault.getHeaderText();
    }


}
