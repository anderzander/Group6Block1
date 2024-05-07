package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.database.MovieEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Movie implements Comparable<Movie> {
    @SerializedName("id")
    public String movieID;
    @SerializedName("title")
    public String title;
    @SerializedName("genres")
    public List<Genre> genres;
    @SerializedName("releaseYear")
    public int releaseYear;
    @SerializedName("description")
    public String description;
    @SerializedName("imgUrl")
    public String imgUrl;
    @SerializedName("lengthInMinutes")
    public int lengthInMinutes;
    @SerializedName("directors")
    public List<String> directors;
    @SerializedName("writers")
    public List<String> writers;
    @SerializedName("mainCast")
    public List<String> mainCast;
    @SerializedName("rating")
    public double rating;



    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

   public Movie(String id, String title, List<Genre> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating) {
       this.movieID = id;
       this.title = title;
       this.genres = genres;
       this.releaseYear = releaseYear;
       this.description = description;
       this.imgUrl = imgUrl;
       this.lengthInMinutes = lengthInMinutes;
       this.directors = directors;
       this.writers = writers;
       this.mainCast = mainCast;
       this.rating = rating;
   }

    public Movie(MovieEntity movieEntity) {
        this.movieID = movieEntity.getApiId();
        this.title = movieEntity.getTitle();

        List<Genre> genres = new ArrayList<>();
        String[] words = movieEntity.getGenres().split(",");
        for (String string : words) {
            String genre = (string.replace(" ", ""));
            genres.add(Genre.valueOf(genre));
        }
        this.genres = genres;

        this.releaseYear = movieEntity.getReleaseYear();
        this.description = movieEntity.getDescription();
        this.imgUrl = movieEntity.getImgUrl();
        this.lengthInMinutes = movieEntity.getLengthInMinutes();
        this.rating = movieEntity.getRating();
    }


    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ").append(movieID).append("\n");
    sb.append("Title: ").append(title).append("\n");
    sb.append("Genres: ").append(genres).append("\n");
    sb.append("Release year: ").append(releaseYear).append("\n");
    sb.append("Description: ").append(description).append("\n");
    sb.append("Img URL: ").append(imgUrl).append("\n");
    sb.append("Length in minutes: ").append(lengthInMinutes).append("\n");
    sb.append("Directors: ").append(directors).append("\n");
    sb.append("Writers: ").append(writers).append("\n");
    sb.append("Main cast: ").append(mainCast).append("\n");
    sb.append("Rating: ").append(rating).append("\n");
    return sb.toString();
}
    @Override
    public int compareTo(Movie other) {
        int compareInt = this.title.compareTo(other.title);
        return Integer.compare(compareInt, 0);
    }
    public String genreListToString() {
        StringJoiner joiner = new StringJoiner(", ");

        for (Genre genre : this.genres) {
            joiner.add(genre.toString());
        }

        return joiner.toString();
    }
}
