
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class HoofdScherm extends AnchorPane implements BeheerstScherm{

	public SchermenController schermenController;
	private Button home;
	private Button mailMenu;
	private Rectangle rectangle;
	private Label bericht;
	@Override
	public void setSchermManager(SchermenController schermController) {
		this.schermenController = schermenController;	
	}
	public HoofdScherm() {
		maakView();
	}
	private void maakView() {
		getStyleClass().add("achtergrond");
		setMinSize(1200,800);
		bericht = new Label("dennisi");
		bericht.setFont(new Font("Consolas", 20));
		getChildren().add(bericht);
		
	}
}
