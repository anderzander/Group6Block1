package at.ac.fhcampuswien.fhmdb.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import at.ac.fhcampuswien.fhmdb.models.Movie;

public class AscendingSort implements SortState {
    @Override
    public List<Movie> sort(List<Movie> movies) {
        Collections.sort(movies, Comparator.comparing(Movie::getTitle));
        return movies;
    }
}
