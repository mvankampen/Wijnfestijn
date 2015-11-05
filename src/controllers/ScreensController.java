package controllers;

import interfaces.ControlledScreen;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
/**
 * <p> This class is used as a screenmanager, so the system
 * has a light flexible screenmanager that doesn't eat much data </p>
 * @author Alex
 * @version v1.0
 *
 */
public class ScreensController extends AnchorPane {
    /**
     * <p>The hashmap that functions as storage for all screens
     * and is primarily used so we can stash views<p>
     */
	
    private HashMap<String, AnchorPane> screens = new HashMap<>();

    /**
    * <p>used to add a screen to the hashmap</p>
    * @param naam used for searching for the view in the hashmap
    * @param anchorPane used to know which anchorPane view to add
    */
    public void screenAdd(String naam, AnchorPane anchorPane) {
        screens.put(naam, anchorPane);
    }
   /**
    * <p>loads in the view, gives him this screencontroller </p>
    * @param naam used for searching for the view in the hashmap
    * @param anchorPane used to know which anchorPane to add
    */
    public void screenLoad(String naam, AnchorPane anchorPane) {
        ControlledScreen screen = (ControlledScreen) anchorPane;
        screen.setScreenController(this);
        screenAdd(naam, anchorPane);
    }
    /**
     * <p>to allow removal of the views in the hashmap</p>
     * @param naam to identify which view to remove
     */
    public void screenRemove(String naam) {
        screens.remove(naam);
    }
    /**
     * <p> loads in the screen and immediality makes it the active view
     *made so controllers can make a new view for themselves easier </p>
     * @param naam used for searching for the view in the hashmap
     * @param anchorPane used to know which anchorPane to add
     */
    
    public void screenLoadSet(String naam, AnchorPane anchorPane) {
    	screenLoad(naam, anchorPane);
    	screenSet(naam);
    }
    /**
     * <p>sets the given view as the view that the user sees</p>
     * @param naam used to know which view to switch to 
     */
    public void screenSet(String naam) {
    	//checks if there is already a view active, if so remove it
        if (screens.get(naam) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, screens.get(naam));
            } else {
                getChildren().add(screens.get(naam));
            }
        }
    }
}
