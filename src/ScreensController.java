import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ScreensController extends AnchorPane {
	//Een hashmap, bezit paren gevormt door de schermen ID's
	private HashMap<String, AnchorPane> schermen = new HashMap<>();
	//methode voor het toevoegen van een scherm
	public void schermToevoegen(String naam, AnchorPane anchorPane){
		schermen.put(naam, anchorPane);
	}
	public void schermLaden(String naam, AnchorPane anchorPane) {
		ControlledScreen scherm = (ControlledScreen) anchorPane;
		scherm.setSchermManager(this);
		schermToevoegen(naam, anchorPane);
	}
	
	public void schermUitladen(String naam){
		schermen.remove(naam);
	}
	
	public void setScherm(String naam){
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
