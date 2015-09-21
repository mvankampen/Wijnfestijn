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
        schermManager.schermLaden(Applet.HOOFDSCHERMID, new HoofdScherm());
        schermManager.setScherm(Applet.HOOFDSCHERMID);
        Group root = new Group();
        root.getChildren().add(schermManager);
        Scene scene = new Scene(root,1680,1050);
        scene.getStylesheets().addAll(this.getClass().getResource("style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

    };

    public static void main(String[] args) {
        launch(args);
    }

}
