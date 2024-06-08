package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {
    private Dao<MovieEntity, Long> daoMovieRepo;

    private static MovieRepository instance;

    private MovieRepository() throws DatabaseException {
        try {
            this.daoMovieRepo = Database.getDatabase().getMovieDao();
        } catch (Exception e) {
            throw new DatabaseException("Error in MovieRepository",e);
        }

    }

    public static MovieRepository getMovieRepository() throws DatabaseException{
        try {
            if (instance == null){
                instance = new MovieRepository();
            }
        } catch (DatabaseException e) {
            throw new DatabaseException(e);
        }
        return instance;
    }


    //readAllMovies
    public List<MovieEntity> readAllMovies() throws SQLException {
        return daoMovieRepo.queryForAll();
    }

    public boolean movieExistsInDB(String title) throws SQLException {
        List<MovieEntity> movies = daoMovieRepo.queryForEq("title", title);
        return !movies.isEmpty();
    }

    public void saveMovieIfNotInDB(Movie movie) throws SQLException, DatabaseException {

        if (!movieExistsInDB(movie.getTitle()))
            try {
                daoMovieRepo.create(new MovieEntity(movie));
                System.out.println("Inserted new movie: " + movie.getTitle());
            } catch (Exception e) {
                throw new DatabaseException("GetDatabase didn't work properly", e);
            }
        else {
            System.out.println("Movie already exists: " + movie.getTitle());
        }


    }
}
