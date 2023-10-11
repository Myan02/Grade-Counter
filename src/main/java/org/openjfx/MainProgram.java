package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.io.IOException;

public class MainProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("New Piechart");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
