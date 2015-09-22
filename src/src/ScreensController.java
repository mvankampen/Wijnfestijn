import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ScreensController extends AnchorPane {
	//Een hashmap, bezit paren gevormt door de schermen ID's
	private HashMap<String, AnchorPane> schermen = new HashMap<>();
	//methode voor het toevoegen van een scherm
	public void screenAdd(String naam, AnchorPane anchorPane){
		schermen.put(naam, anchorPane);
	}
	public void screenLoad(String naam, AnchorPane anchorPane) {
		ControlledScreen screen = (ControlledScreen) anchorPane;
		screen.setScreenController(this);
		screenAdd(naam, anchorPane);
	}
	
	public void screenRemove(String naam){
		schermen.remove(naam);
	}
	
	void screenSet(String naam){
		if (schermen.get(naam) != null) {
			if (!getChildren().isEmpty()) {
				getChildren().remove(0);
				getChildren().add(0,schermen.get(naam));
			} else {
				getChildren().add(schermen.get(naam));
			}
		}
	}
	
}
