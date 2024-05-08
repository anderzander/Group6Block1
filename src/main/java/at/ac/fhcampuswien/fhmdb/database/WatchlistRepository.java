package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {
    private  Dao<WatchlistMovieEntity, Long> daoWatchlistRepo;

    public WatchlistRepository() throws DatabaseException {
        try {
            this.daoWatchlistRepo = Database.getDatabase().getWatchlistDao();
        } catch (DatabaseException e) {
            throw new DatabaseException("GetDatabase didn't work properly", e);
        }

    }

    public  List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return daoWatchlistRepo.queryForAll();
    }

    public boolean watchlistExistsInDB(String apiId) throws SQLException {
        List<WatchlistMovieEntity> movies = daoWatchlistRepo.queryForEq("apiId", apiId);
        return !movies.isEmpty();
    }

    public void saveWatchlistEntityIfNotInDB(Movie movie) throws SQLException {
        if (!watchlistExistsInDB(movie.getMovieID())) {
            daoWatchlistRepo.create(new WatchlistMovieEntity(movie));
            System.out.println("Inserted new movie: " + movie.getTitle());
        } else {
            System.out.println("Movie already exists: " + movie.getTitle());
        }

    }

    public  List<Movie> getMoviesFromWatchlist() throws SQLException, DatabaseException {

        Dao<MovieEntity, Long> daoMovieRepo = Database.getDatabase().getMovieDao();


        List<MovieEntity> entities = new ArrayList<>();

        for (WatchlistMovieEntity entity : this.getWatchlist()) {
            List<MovieEntity> temp = daoMovieRepo.queryForEq("apiId", entity.getApiId());
            entities.addAll(temp);
        }

        List<Movie> moviesFromWatchlist = new ArrayList<>();
        for (MovieEntity entity: entities) {
            moviesFromWatchlist.add(new Movie(entity));
        }

        return moviesFromWatchlist;
    }


}
