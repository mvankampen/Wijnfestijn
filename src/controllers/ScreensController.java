package controllers;

import interfaces.ControlledScreen;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class ScreensController extends AnchorPane {
    /*The hashmap that functions as storage for all screens
     * and is primarily used so we can stash views
     */
	
    private HashMap<String, AnchorPane> screens = new HashMap<>();

    /*used to add a screen to the hashmap, together with a string 
    * to make it easier to find the needed view
    */
    public void screenAdd(String naam, AnchorPane anchorPane) {
        screens.put(naam, anchorPane);
    }
    //loads in the view, gives him this screencontroller 
    public void screenLoad(String naam, AnchorPane anchorPane) {
        ControlledScreen screen = (ControlledScreen) anchorPane;
        screen.setScreenController(this);
        screenAdd(naam, anchorPane);
    }
    //to allow removal of the views in the hashmap
    public void screenRemove(String naam) {
        screens.remove(naam);
    }
    /*loads in the screen and immediality makes it the active view
     * made so controllers can make a new view for themselves easier
     */
    public void screenLoadSet(String naam, AnchorPane anchorPane) {
    	screenLoad(naam, anchorPane);
    	screenSet(naam);
    }
    //sets the given view as the view that the user sees
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
