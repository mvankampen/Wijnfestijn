package splashscreens;

/**
 * Created by Alex on 07-10-15.
 */
public class SplashSurnameMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

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
