package at.ac.fhcampuswien.fhmdb.sort;

import java.util.List;
import at.ac.fhcampuswien.fhmdb.models.Movie;

public class UnsortedState implements SortState {
    @Override
    public List<Movie> sort(List<Movie> movies) {
        return movies;
    }
}

