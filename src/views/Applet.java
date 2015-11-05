/**
 * Copyright (c) <2015> <Hogeschool Leiden>
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package views;

import controllers.ControllersController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * <p>Starts the main application.</p>
 *
 * @author Sander de Jong
 * @author Patrick van der Plas
 * @author Alex van der Wal
 * @author Michael van Kampen
 * @author Dennis Sloove
 */

public class Applet extends Application {
    private ControllersController CC;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param stage
     * @throws SQLException
     */
    public void start(Stage stage) throws SQLException {
        // workaround for a combobox crash
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
