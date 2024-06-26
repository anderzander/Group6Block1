package at.ac.fhcampuswien.fhmdb.observer;

import at.ac.fhcampuswien.fhmdb.models.Movie;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Movie movie, String message);
}