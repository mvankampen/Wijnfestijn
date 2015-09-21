import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class testView extends Application{

	private NavigationController createNav;
	
	public static void main(String args[]){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		createNav = new NavigationController();
		StackPane root = new StackPane();
		root.getChildren().add(createNav);
		
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}

}
