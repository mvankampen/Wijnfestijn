package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty surname field </p>
 *
 * @author Alex van der wal
 */
public class SplashSurnameMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public SplashSurnameMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return splashDefault.getContextText() + "foutieve achternaam\n";

    }

    public String getTitleText() {
        return splashDefault.getTitleText();
    }

    public String getHeaderText() {
        return splashDefault.getHeaderText();
    }


}
