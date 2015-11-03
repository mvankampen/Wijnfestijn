package controllers;

import interfaces.ControlledScreen;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class ScreensController extends AnchorPane {
    /*The hashmap that functions as storage for all screens
     * and is primarily used so we can stash views
     */
	
    private HashMap<String, AnchorPane> schermen = new HashMap<>();

    /*used to add a screen to the hashmap, together with a string 
    * to make it easier to find the needed view
    */
    public void screenAdd(String naam, AnchorPane anchorPane) {
        schermen.put(naam, anchorPane);
    }

    public void screenLoad(String naam, AnchorPane anchorPane) {
        ControlledScreen screen = (ControlledScreen) anchorPane;
        screen.setScreenController(this);
        screenAdd(naam, anchorPane);
    }

    public void screenRemove(String naam) {
        schermen.remove(naam);
    }
    
    public void screenLoadSet(String naam, AnchorPane anchorPane) {
    	screenLoad(naam, anchorPane);
    	screenSet(naam);
    }
    
    public void screenSet(String naam) {
        if (schermen.get(naam) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, schermen.get(naam));
            } else {
                getChildren().add(schermen.get(naam));
            }
        }
    }
}
