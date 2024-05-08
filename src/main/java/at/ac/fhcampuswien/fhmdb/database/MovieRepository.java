package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {
    private Dao<MovieEntity, Long> daoMovieRepo;

    public MovieRepository() {
        this.daoMovieRepo = Database.getDatabase().getMovieDao();
    }


    //readAllMovies
    public List<MovieEntity> readAllMovies() throws SQLException {
        return daoMovieRepo.queryForAll();
    }

    public boolean movieExistsInDB(String title) throws SQLException {
        List<MovieEntity> movies = daoMovieRepo.queryForEq("title", title);
        return !movies.isEmpty();
    }

    public void saveMovieIfNotInDB(Movie movie) throws SQLException {
        if (!movieExistsInDB(movie.getTitle())) {
            daoMovieRepo.create(new MovieEntity(movie));
            System.out.println("Inserted new movie: " + movie.getTitle());
        } else {
            System.out.println("Movie already exists: " + movie.getTitle());
        }

    }
}
