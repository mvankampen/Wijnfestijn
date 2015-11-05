package interfaces;

import controllers.ScreensController;

/**
 * <P>Sets the splashscreen view to the new splash screen and returns this splashscreen so that it can be used.</P>
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */
public interface ControlledScreen {
    /**
     * @param screensController
     */
    public void setScreenController(ScreensController screensController);
}
