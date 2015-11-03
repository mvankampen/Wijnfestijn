package views;

import controllers.ControllersController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Applet extends Application {
    private ControllersController CC;

    public static void main(String[] args) {
        launch(args);
    }
 
    public void start(Stage stage) throws SQLException {
        // workaround a combobox crash
        System.setProperty("glass.accessible.force", "false");
        //Controller that is responsible for creating all controllers and views
        this.CC = new ControllersController();
        Group root = new Group();
        root.getChildren().addAll(CC.getScreensController(), this.CC.getNavigationView());
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets()
            .addAll(this.getClass().getResource("../style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.setTitle("A WarnerBrothers Product");
        stage.show();
    }
}
