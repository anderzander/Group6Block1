package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static at.ac.fhcampuswien.fhmdb.models.Genre.getAllGenres;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;
    @FXML
    public JFXComboBox releaseYearComboBox;
    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXButton sortBtn;
    @FXML
    public JFXButton resetBtn;


    public static final List<Movie> allMovies;

    static {
        try {
            allMovies = MovieAPI.getMoviesFromApi();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        for (Genre genre : getAllGenres()) {
            genreComboBox.getItems().add(genre.getGenreAsString());
        }
        ratingComboBox.setPromptText("Filter by Rating");
        releaseYearComboBox.setPromptText("Filter by Release Year");

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here


        // Sort button example:
        sortBtn.setOnAction(sortEvent -> {
            sortObservableMovies(sortBtn.getText());
        });

        resetBtn.setOnAction(actionEvent -> {
            //TODO Text isn't grey!!
            searchField.clear();
            genreComboBox.setValue(null);

            observableMovies.clear();

            observableMovies.addAll(allMovies);
            movieListView.setItems(observableMovies);// set data of observable list to list view

            movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data
        });

        searchBtn.setOnAction(sortEvent -> {
            filterObservableMovies(searchField.getText(), (String) genreComboBox.getValue());
        });

    }

    public void sortObservableMovies(String textFromSortBtn){
        if(textFromSortBtn.equals("Sort (asc)")) {
            // TODO sort observableMovies ascending
            Collections.sort(observableMovies);
            if (sortBtn != null){
                sortBtn.setText("Sort (desc)");
            }

        } else {
            // TODO sort observableMovies descending
            observableMovies.sort(Collections.reverseOrder());
            if (sortBtn != null){
                sortBtn.setText("Sort (asc)");
            }
        }
    }

    public void filterObservableMovies(String searchField, String genreComboBox) {


        List<Movie> list1 = listFilteredByGenres(allMovies, genreComboBox);
        List<Movie> list2 = listFilteredBySearchField(allMovies, searchField);


        list1.retainAll(list2);

        ObservableList<Movie> finishedFilteredList = FXCollections.observableArrayList();
        finishedFilteredList.addAll(list1);


        movieListView.setItems(finishedFilteredList);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        observableMovies = finishedFilteredList;

    }



    public List<Movie> listFilteredByGenres(List<Movie> MovieListToFilter, String genreComboBox){

        if (genreComboBox == null){
            return new ArrayList<>(allMovies);
        }

        Set<Movie> filteredMovieSetByGenre = new HashSet<>();


        for (Movie movie : MovieListToFilter) {
            for (Genre genre : movie.getGenres()) {
                if (genre.getGenreAsString().equals(genreComboBox) && !filteredMovieSetByGenre.contains(movie)){
                    filteredMovieSetByGenre.add(movie);
                }
            }
        }

        return new ArrayList<>(filteredMovieSetByGenre);
    }


    public List<Movie> listFilteredBySearchField(List<Movie> movieList, String searchField){

        Set<Movie> filteredMovieSetBySearchField = new HashSet<>();
        String toSearch = searchField.toLowerCase();

        for (Movie movie : movieList) {
            if (movie.getTitle().toLowerCase().contains(toSearch) || movie.getDescription().toLowerCase().contains(toSearch)){
                filteredMovieSetBySearchField.add(movie);
            }
        }

        return new ArrayList<>(filteredMovieSetBySearchField);
    }


}

