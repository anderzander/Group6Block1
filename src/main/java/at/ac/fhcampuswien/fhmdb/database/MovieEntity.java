package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "movie")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    public String apiId;
    @DatabaseField
    public String title;
    @DatabaseField
    public String description;
    @DatabaseField
    public String genres;
    @DatabaseField
    public int releaseYear;
    @DatabaseField
    public String imgUrl;
    @DatabaseField
    public int lengthInMinutes;
    @DatabaseField
    public double rating;


    public MovieEntity() {
    }

    public MovieEntity(Movie movie) {
        this.apiId = movie.getMovieID();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.genres = movie.genreListToString();
        this.releaseYear = movie.getReleaseYear();
        this.imgUrl = movie.getImgUrl();
        this.lengthInMinutes = movie.getLengthInMinutes();
        this.rating = movie.getRating();
    }

    public long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    List<MovieEntity> fromMovies(List<Movie> movies){
        List<MovieEntity> output = new ArrayList<>();
        for (Movie movie: movies) {
            output.add(new MovieEntity(movie));
        }
        return output;
    }

    List<Movie> toMovies(List<MovieEntity> movieEntities){
        List<Movie> output = new ArrayList<>();
        for (MovieEntity movieEntity: movieEntities) {
            output.add(new Movie(movieEntity));
        }
        return output;
    }
}

