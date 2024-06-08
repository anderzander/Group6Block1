package at.ac.fhcampuswien.fhmdb.observer;

import at.ac.fhcampuswien.fhmdb.models.Movie;

public interface Observer {
    void update(Movie movie, String success);
}