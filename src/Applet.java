import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applet extends Application {
    private static Stage stage;
    private static SchermenController schermManager;
    private static final String HOOFDSCHERMID = "hoofdscherm";
    

    public void start(Stage stage) {
        schermManager = new SchermenController();
        //schermManager.schermLaden(Applet.HOOFDSCHERMID, new Hoofdscherm());
        Group root = new Group();
        root.getChildren().add(schermManager);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(1200);
        stage.setResizable(false);
        stage.show();

    };

    public static void main(String[] args) {
        launch(args);
    }

}
