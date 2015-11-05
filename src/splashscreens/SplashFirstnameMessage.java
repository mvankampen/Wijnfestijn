package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty name field </p>
 *
 * @author Alex van der wal
 */
public class SplashFirstnameMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public SplashFirstnameMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return splashDefault.getContextText() + "foutieve voornaam\n";

    }

    public String getTitleText() {
        return splashDefault.getTitleText();
    }

    public String getHeaderText() {
        return splashDefault.getHeaderText();
    }


}
