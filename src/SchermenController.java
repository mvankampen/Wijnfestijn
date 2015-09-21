import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class SchermenController extends StackPane {
	//Een hashmap, bezit paren gevormt door de schermen ID's
	private HashMap<String, StackPane> schermen = new HashMap<>();
	//methode voor het toevoegen van een scherm
	public void schermToevoegen(String naam, StackPane stackPane){
		schermen.put(naam, stackPane);
	}
	public void schermLaden(String naam, StackPane stackPane) {
		BeheerstScherm scherm = (BeheerstScherm) stackPane;
		scherm.setSchermManager(this);
		schermToevoegen(naam, stackPane);
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
