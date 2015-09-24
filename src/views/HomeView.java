
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class HomeView extends AnchorPane implements ControlledScreen{

	private ScreensController screensController;

	public void setScreenController(ScreensController screensController) {
		this.screensController = screensController;
	}

	public HomeView() {
		createView();
	}
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		setUpContentPane();
	}
	
	public void setUpContentPane(){
		//Create introduction HBox
		HBox introBox = new HBox();
		introBox.setLayoutY(200);
		introBox.setLayoutX(100);
		//Create options Pane
		GridPane optionsPane = new GridPane();
		optionsPane.setLayoutY(200);
		
		//intro Box
		Label introLabel = new Label("Dit menu is voorzien van alle directe links naar alle functies."
				+ " Klik op de actie die u wilt ondernemen");
		introBox.getChildren().add(introLabel);
		
		//Label texts row 1
		String orderTitle = "Bestellijst opties";
		String orderText1 = "Printen persoonlijke bestellijsten";
		String orderText2 = "Invoeren van de persoonlijke bestellijsten (maakt orders en facturen)";
		String orderText3 = "Maak een nieuwe bestellijst aan(Doormiddel van CSV";
		String orderText4 = "Bewerk of verwijder een al bestaande bestellijst";
		String debtorsTitle = "Debiteuren opties";
		String debtorText = "Genereer de debiteurenlijst";
		
		//Label texts row 2
		String mailTitle = "Mail opties";
		String mailText1 = "Maak en verstuur een uitnodigingsmail";
		String mailText2 = "Maak en verstuur een bedankmail";
		String mailText3 = "Maak en verstuur een herinneringsmail";
		String customerTitle = "Klanten opties";
		String customerText1 = "Bewerk of verwijder bestaande klanten";
		String customerText2 = "Open het klanten registreren scherm";
		
		//Creating labels row 1
		Label orderTitleLabel = new Label(orderTitle);
		orderTitleLabel.getStyleClass().add("homeview_title");
		Label orderLabel1 = new Label(orderText1);
		Label orderLabel2 = new Label(orderText2);
		Label orderLabel3 = new Label(orderText3);
		Label orderLabel4 = new Label(orderText4);
		Label debtorsTitleLabel = new Label(debtorsTitle);
		debtorsTitleLabel.getStyleClass().add("homeview_title");
		Label debtorsLabel = new Label(debtorText);
		
		//Creating label row 2
		Label mailTitleLabel = new Label(mailTitle);
		mailTitleLabel.getStyleClass().add("homeview_title");
		Label mailLabel1 = new Label(mailText1);
		Label mailLabel2 = new Label(mailText2);
		Label mailLabel3 = new Label(mailText3);
		Label customerTitleLabel = new Label(customerTitle);
		customerTitleLabel.getStyleClass().add("homeview_title");
		Label customerLabel1 = new Label(customerText1);
		Label customerLabel2 = new Label(customerText2);
		
		//Adding labels row 1
		optionsPane.add(new Label(), 1, 1);
		optionsPane.add(orderTitleLabel, 1, 2);
		optionsPane.add(orderLabel1, 1, 3);
		optionsPane.add(orderLabel2, 1, 4);
		optionsPane.add(orderLabel3, 1, 5);
		optionsPane.add(orderLabel4, 1, 6);
		optionsPane.add(debtorsTitleLabel, 1, 7);
		optionsPane.add(debtorsLabel, 1, 8);
		
		//Adding label row 2
		optionsPane.add(mailTitleLabel, 2, 2);
		optionsPane.add(mailLabel1, 2, 3);
		optionsPane.add(mailLabel2, 2, 4);
		optionsPane.add(mailLabel3, 2, 5);
		optionsPane.add(customerTitleLabel, 2, 6);
		optionsPane.add(customerLabel1, 2, 7);
		optionsPane.add(customerLabel2, 2, 8);
		
		optionsPane.setHgap(100);
		optionsPane.setVgap(10);
		
		getChildren().addAll(introBox, optionsPane);
	}
}

