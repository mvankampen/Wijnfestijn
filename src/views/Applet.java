package views;

import controllers.ControllersController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class Applet extends Application {
    private static Stage stage;
    private ControllersController CC;

    public static void main(String[] args) {
        launch(args);
    }
 
    public void start(Stage stage) throws SQLException {
        // workaround a combobox crash
        System.setProperty("glass.accessible.force", "false");
        //maakt de controller voor de schermen aan, handelt het display van de schermen af.
        this.CC = new ControllersController();
        //zet welk scherm er actief moet zijn
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
        File file = new File(".");
        System.out.println(new File("DEFAULTMAIL.txt").getAbsolutePath());
        for(String fileNames : file.list()) System.out.println(fileNames);


    }
}
