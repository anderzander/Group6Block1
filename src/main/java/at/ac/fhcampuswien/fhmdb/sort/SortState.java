package at.ac.fhcampuswien.fhmdb.sort;

import java.util.List;
import at.ac.fhcampuswien.fhmdb.models.Movie;

public interface SortState {
    List<Movie> sort(List<Movie> movies);
}
