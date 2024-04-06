package at.ac.fhcampuswien.fhmdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static at.ac.fhcampuswien.fhmdb.MovieAPI.getMoviesFromApi;
import static at.ac.fhcampuswien.fhmdb.MovieAPI.printMoviesDetails;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 620);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(HomeController.getMostPopularActor(HomeController.allMovies));
        //printMoviesDetails(getMoviesFromApi());
        launch();
    }
}