package at.ac.fhcampuswien.fhmdb.ui;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import at.ac.fhcampuswien.fhmdb.database.Database;
import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final Button addToMovieDbButton = new Button("Add to watchlist");
    private final VBox layout = new VBox(title, detail, genres, releaseYear, rating, addToMovieDbButton);
    private double sceneWith;
    WatchlistRepository watchlistRepository;

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        try {
            watchlistRepository = new WatchlistRepository();
        } catch (DatabaseException e) {
            System.out.println("Couldn't create watchlistRepository in Moviecell");
        }

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            genres.setText("GENRE: " + movie.genreListToString());
            releaseYear.setText("RELEASE YEAR: " + movie.getReleaseYear());
            rating.setText("RATING: " + movie.getRating());


            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-white");
            releaseYear.getStyleClass().add("text-year");
            rating.getStyleClass().add("text-rating");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout

            title.fontProperty().set(title.getFont().font(20));

            if (this.getScene() != null){
                sceneWith = this.getScene().getWidth();
            }
            detail.setMaxWidth(getCurrentSceneWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);

            addToMovieDbButton.setOnMouseClicked(mouseEvent ->{
                try {
                   watchlistRepository.saveWatchlistEntityIfNotInDB(movie);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    public double getCurrentSceneWidth(){

        if (this.getScene() == null){
            return this.sceneWith;
        } else {
            return this.getScene().getWidth();
        }
    }



}

