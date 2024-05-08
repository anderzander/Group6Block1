package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {
    private Dao<WatchlistMovieEntity, Long> daoWatchlistRepo;

    public WatchlistRepository() {
        this.daoWatchlistRepo = Database.getDatabase().getWatchlistDao();
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

    public List<Movie> getMoviesFromWatchlist() throws SQLException {

        Dao<MovieEntity, Long> daoMovieRepo = Database.getDatabase().getMovieDao();

        List<MovieEntity> movieEntities = daoMovieRepo.queryBuilder()
                .join(daoMovieRepo.queryBuilder())
                .where()
                .eq("apiId", "apiID")
                .query();

        List<Movie> moviesFromWatchlist = new ArrayList<>();

        for (MovieEntity entity : movieEntities) {
        moviesFromWatchlist.add(new Movie(entity));
        }
        return moviesFromWatchlist;
    }


}
