package splashscreens;

/**
 * <p> A wrapper for the decorator, adds a error message for registration - empty city field </p>
 *
 * @author Alex van der wal on 07-10-15.
 */
public class MailSendMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    /**
     * @param splashDefault so that it can wrap itself around the object given
     */
    public MailSendMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "Uw mail is verstuurd naar de gewenste doelgroep";
    }

    public String getTitleText() {
        return "Succesvolle verzending";
    }

    public String getHeaderText() {
        return "opdracht voltooid";
    }


}
