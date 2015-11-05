package splashscreens;

public class AdjustCompleteMessage extends SplashDefaultMessage {
    SplashDefault splashDefault;

    public AdjustCompleteMessage(SplashDefault splashDefault) {
        this.splashDefault = splashDefault;
    }

    @Override public String getContextText() {
        return "U kunt het scherm sluiten";

    }

    public String getTitleText() {
        return "Voltooid";
    }

    public String getHeaderText() {
        return "De aanpassingen zijn gelukt";
    }


}
