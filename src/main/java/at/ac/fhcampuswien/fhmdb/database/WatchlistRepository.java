package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.observer.Observable;
import at.ac.fhcampuswien.fhmdb.observer.Observer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {
    private Dao<WatchlistMovieEntity, Long> daoWatchlistRepo;
    private static WatchlistRepository instance;
    private final List<Observer> observers = new ArrayList<>();

    private WatchlistRepository() throws DatabaseException {
        try {
            this.daoWatchlistRepo = Database.getDatabase().getWatchlistDao();
        } catch (DatabaseException e) {
            throw new DatabaseException("GetDatabase didn't work properly", e);
        }
    }

    public static WatchlistRepository getWatchlistRepository() throws DatabaseException {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return daoWatchlistRepo.queryForAll();
    }

    public boolean watchlistExistsInDB(String apiId) throws SQLException {
        List<WatchlistMovieEntity> movies = daoWatchlistRepo.queryForEq("apiId", apiId);
        return !movies.isEmpty();
    }

    public void saveWatchlistEntityIfNotInDB(Movie movie) throws SQLException {
        if (!watchlistExistsInDB(movie.getMovieID())) {
            daoWatchlistRepo.create(new WatchlistMovieEntity(movie));
            notifyObservers(movie, "Movie added to watchlist: " + movie.getTitle());
        } else {
            notifyObservers(movie, "Movie already in watchlist: " + movie.getTitle());
        }
    }

    public void removeFromWatchlist(Movie movie) throws SQLException {
        DeleteBuilder<WatchlistMovieEntity, Long> deleteBuilder = daoWatchlistRepo.deleteBuilder();
        deleteBuilder.where().eq("apiId", movie.getMovieID());
        deleteBuilder.delete();
        notifyObservers(movie, "Movie removed from watchlist: " + movie.getTitle());
    }

    public List<Movie> getMoviesFromWatchlist() throws SQLException, DatabaseException {
        Dao<MovieEntity, Long> daoMovieRepo = Database.getDatabase().getMovieDao();
        List<MovieEntity> entities = new ArrayList<>();

        for (WatchlistMovieEntity entity : this.getWatchlist()) {
            List<MovieEntity> temp = daoMovieRepo.queryForEq("apiId", entity.getApiId());
            entities.addAll(temp);
        }

        List<Movie> moviesFromWatchlist = new ArrayList<>();
        for (MovieEntity entity : entities) {
            moviesFromWatchlist.add(new Movie(entity));
        }

        return moviesFromWatchlist;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Movie movie, String message) {
        for (Observer observer : observers) {
            observer.update(movie, message);
        }
    }
}
