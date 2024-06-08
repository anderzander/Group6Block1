package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.*;
import at.ac.fhcampuswien.fhmdb.observer.Observer;
import at.ac.fhcampuswien.fhmdb.sort.AscendingSort;
import at.ac.fhcampuswien.fhmdb.sort.DescendingSort;
import at.ac.fhcampuswien.fhmdb.sort.MovieSort;
import at.ac.fhcampuswien.fhmdb.sort.UnsortedState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.database.MovieEntity.toMovies;


public class HomeController implements Initializable, Observer {


    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;
    @FXML
    public JFXListView watchlistView;
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
    @FXML
    public MenuItem homeBtn;

    @FXML
    public MenuItem watchlistBtn;


    static boolean inHomeNavigation = true;


    public static List<Movie> allMovies = new ArrayList<>();
    public static List<Integer> releaseYearList = new ArrayList<>();
    private MovieSort movieSort = new MovieSort();
    private AscendingSort ascendingSort = new AscendingSort();
    private DescendingSort descendingSort = new DescendingSort();
    private UnsortedState unsortedState = new UnsortedState();
    private WatchlistRepository watchlistRepository;

    private static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    public MovieRepository moviesToDB;

    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    public static boolean isInHomeNavigation() {
        return inHomeNavigation;
    }

    static final String base = "https://prog2.fh-campuswien.ac.at/movies";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            moviesToDB = MovieRepository.getMovieRepository();
        } catch (DatabaseException e) {
            showErrorPopup("Couldn't create moviesToDB in HomeController", e.getMessage());
        } catch (Exception e) {
            showErrorPopup("Couldn't create moviesToDB in HomeController (Nullpointer)", e.getMessage());
        }


        try {
            allMovies = MovieAPI.getMoviesFromApi("https://prog2.fh-campuswien.ac.at/movies");
            for (Movie movie : allMovies) {
                moviesToDB.saveMovieIfNotInDB(movie);
            }
        } catch (NullPointerException e) {
            showErrorPopup("No connection to API", e.getMessage());
            //ToDo exception handling: Anzeigen das Keine Internetverbindung besteht und die Movies von der Datenbank verwendet Werden
            try {
                allMovies = toMovies(moviesToDB.readAllMovies());
            } catch (SQLException | NullPointerException ex) {
                showErrorPopup("Couldn't get movies from database", e.getMessage());
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        } catch (DatabaseException e) {
            showErrorPopup("Something went wrong with the database", e.getMessage());
        } catch (MovieApiException e) {
            showErrorPopup("Couldn't get movies from API", e.getMessage());
        }

        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data


        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.getItems().addAll(Rating.getAllValues());
        releaseYearComboBox.setPromptText("Filter by Release Year");
        for (Movie movie : allMovies) {
            if (!releaseYearList.contains(movie.getReleaseYear())) {
                releaseYearList.add(movie.getReleaseYear());
            }
        }
        Collections.sort(releaseYearList);
        releaseYearComboBox.getItems().addAll(releaseYearList);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here


        sortBtn.setOnAction(sortEvent -> {
            sortObservableMovies();
        });


        resetBtn.setOnAction(actionEvent -> {
            //TODO Text isn't grey!!
            searchField.clear();
            genreComboBox.setValue(null);
            ratingComboBox.setValue(null);
            releaseYearComboBox.setValue(null);

            observableMovies.clear();

            try {
                allMovies = MovieAPI.getMoviesFromApi("https://prog2.fh-campuswien.ac.at/movies");
            } catch (MovieApiException e) {
                showErrorPopup("Couldn't get movies from API", e.getMessage());
            }

            observableMovies.addAll(allMovies);
            movieSort.setState(unsortedState); // Initial auf unsorted setzen
            movieSort.sort(observableMovies);   // Hier sortieren
            movieListView.setItems(observableMovies);   // set data of observable list to list view
            movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        });

        searchBtn.setOnAction(sortEvent -> {

            try {
                //allMovies = MovieAPI.getMoviesFromApi(MovieAPI.urlForFilteredList(releaseYearComboBox.getValue(), ratingComboBox.getValue()));
                allMovies = MovieAPI.getMoviesFromApi(new MovieApiRequestBuilder(base)
                        .releaseYear(releaseYearComboBox.getValue())
                        .ratingFrom(ratingComboBox.getValue())
                        .build());
            } catch (MovieApiException e) {
                showErrorPopup("Couldn't get movies from API", e.getMessage());
            }


            if (genreComboBox.getValue() != null) {
                filterObservableMovies(searchField.getText(), genreComboBox.getValue().toString());
            } else {
                filterObservableMovies(searchField.getText(), null);
            }

        });
        homeBtn.setOnAction(actionEvent -> {
            releaseYearComboBox.setVisible(true);
            ratingComboBox.setVisible(true);
            try {
                allMovies = toMovies(moviesToDB.readAllMovies());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resetBtn.setVisible(true);
            searchBtn.setVisible(true);
            searchField.setVisible(true);
            genreComboBox.setVisible(true);
            inHomeNavigation = true;
            observableMovies.clear();
            observableMovies.addAll(allMovies);


        });
        watchlistBtn.setOnAction(actionEvent -> {
            releaseYearComboBox.setVisible(false);
            ratingComboBox.setVisible(false);
            resetBtn.setVisible(false);
            searchBtn.setVisible(false);
            searchField.setVisible(false);
            genreComboBox.setVisible(false);
            inHomeNavigation = false;

            try {
                WatchlistRepository repository = WatchlistRepository.getMovieRepository();
                observableMovies.clear();
                observableMovies.addAll(repository.getMoviesFromWatchlist());
            } catch (SQLException e) {
                showErrorPopup("Couldn't get movies from API", e.getMessage());
            } catch (Exception e) {

            }
        });


    }

    public static void refreshWatchlist() {
        try {
            WatchlistRepository repository = WatchlistRepository.getMovieRepository();
            observableMovies.clear();
            observableMovies.addAll(repository.getMoviesFromWatchlist());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
        }
    }


    public void sortObservableMovies() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            movieSort.setState(ascendingSort);
            sortBtn.setText("Sort (desc)");
        } else {
            movieSort.setState(descendingSort);
            sortBtn.setText("Sort (asc)");
        }
        movieSort.sort(observableMovies);
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

    public List<Movie> listFilteredByGenres(List<Movie> MovieListToFilter, String genreComboBox) {

        if (genreComboBox == null) {
            return new ArrayList<>(allMovies);
        }

        Set<Movie> filteredMovieSetByGenre = new HashSet<>();


        for (Movie movie : MovieListToFilter) {
            for (Genre genre : movie.getGenres()) {
                if (genre.toString().equalsIgnoreCase(genreComboBox)) {
                    filteredMovieSetByGenre.add(movie);
                }
            }
        }

        return new ArrayList<>(filteredMovieSetByGenre);
    }

    public List<Movie> listFilteredBySearchField(List<Movie> movieList, String searchField) {

        Set<Movie> filteredMovieSetBySearchField = new HashSet<>();
        String toSearch = searchField.toLowerCase();

        for (Movie movie : movieList) {
            if (movie.getTitle().toLowerCase().contains(toSearch) || movie.getDescription().toLowerCase().contains(toSearch)) {
                filteredMovieSetBySearchField.add(movie);
            }
        }

        return new ArrayList<>(filteredMovieSetBySearchField);
    }

    public static String getMostPopularActor(List<Movie> movieList) {
        Map<String, Long> actorCount = movieList.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        // Schauspieler sortieren nach Anzahl der Filme
        Optional<Map.Entry<String, Long>> mostPopularActor = actorCount.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        // Den Schauspieler mit den meisten Filmen als String zurückgeben
        return mostPopularActor.map(Map.Entry::getKey).orElse("");
    }

    public static int getLongestMovieTitle(List<Movie> movieList) {
        return movieList.stream()
                .map(Movie::getTitle)   //Umwandlung String Movie zu Stream Movie
                .max(Comparator.comparingInt(String::length))   // mit Compare den längsten Film finden
                .map(String::length)           // String länge wird in int umgewandelt
                .orElse(0);              // es wird 0 zurück gegebn, wenn kein Title in der Liste is

    }

    public static long countMoviesFrom(List<Movie> movieList, String director) {
        return movieList.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    public static List<Movie> getMoviesBetweenYears(List<Movie> movieList, int startYear, int endYear) {
        return movieList.stream()
                .filter(movie -> movie.releaseYear >= startYear && movie.releaseYear <= endYear)
                .collect(Collectors.toList());


    }
    public HomeController() {
        try {
            watchlistRepository = WatchlistRepository.getMovieRepository();
            watchlistRepository.addObserver(this);
        } catch (DatabaseException e) {
            showErrorPopup("Couldn't create WatchlistRepository in HomeController", e.getMessage());
        }
    }

    @Override
    public void update(Movie movie, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Watchlist Update");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }


    public void showErrorPopup(String error, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong, buddy!");
        alert.setHeaderText(error);
        //Important: message has to be e.message!
        alert.setContentText(message);

        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);

        // Get the close button and attach an event handler to close the dialog
        Button closeButtonNode = (Button) alert.getDialogPane().lookupButton(closeButton);
        closeButtonNode.setOnAction(e -> alert.close());

        alert.showAndWait();

    }


}

