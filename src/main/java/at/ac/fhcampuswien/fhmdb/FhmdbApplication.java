package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.factory.MyFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        MyFactory myFactory = new MyFactory();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
        fxmlLoader.setControllerFactory(myFactory);

        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        scene.getStylesheets().add(Objects.requireNonNull(HomeController.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();

    }
}