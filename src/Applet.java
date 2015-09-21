import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applet extends Application {
    private static Stage stage;
    private static SchermenController schermManager;
    private static final String HOOFDSCHERMID = "bestellijstprintview";
    
    public void start(Stage stage) {
        schermManager = new SchermenController();
        schermManager.schermLaden(Applet.HOOFDSCHERMID, new BestellijstPrintView());
        schermManager.setScherm(Applet.HOOFDSCHERMID);
        Group root = new Group();
        root.getChildren().add(schermManager);
        Scene scene = new Scene(root,1200,800);
        scene.getStylesheets().addAll(this.getClass().getResource("style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.show();

    };

    public static void main(String[] args) {
        launch(args);
    }

}
