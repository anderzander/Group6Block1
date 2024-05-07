package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {
    private Dao<MovieEntity, Long> dao;

    public MovieRepository(){
        this.dao = Database.getDatabase().getDao();
    }

    public void addToWatchlist(Movie movie) throws SQLException {
        dao.createIfNotExists(movieToMovieEntity(movie));
    }

    public void removeFromWatchlist(Movie movie) throws SQLException {
        dao.delete(movieToMovieEntity(movie));
    }

    private MovieEntity movieToMovieEntity(Movie movie){
        return new MovieEntity(movie);
    }

    public List<MovieEntity> readAllContacts() throws SQLException{
        return dao.queryForAll();
    }
}
