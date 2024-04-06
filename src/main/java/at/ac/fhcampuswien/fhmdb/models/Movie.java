package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Movie implements Comparable<Movie> {
    @SerializedName("id")
    public String id;
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

//   public Movie(String id, String title, List<String> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating) {
//       this.id = id;
//       this.title = title;
//       this.genres = genres;
//       this.releaseYear = releaseYear;
//       this.description = description;
//       this.imgUrl = imgUrl;
//       this.lengthInMinutes = lengthInMinutes;
//       this.directors = directors;
//       this.writers = writers;
//       this.mainCast = mainCast;
//       this.rating = rating;
//   }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

/*

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String genreListToString() {
        StringJoiner joiner = new StringJoiner(", ");

        for (Genre genre : this.genres) {
            joiner.add(genre.getGenreAsString());
        }

        return joiner.toString();
    }

    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here

        String descriptionForInterstellar =
                "\"Interstellar\" is a visually stunning and emotionally gripping sci-fi epic that follows astronauts on a perilous journey through a wormhole in search of a new home for humanity, exploring themes of love, sacrifice, and the resilience of the human spirit.";
        List<Genre> genreListForInterstellar = new ArrayList<>();
        genreListForInterstellar.add(new Genre("ACTION"));
        genreListForInterstellar.add(new Genre("SCIENCE_FICTION"));
        Movie interstellar = new Movie("Interstellar", descriptionForInterstellar, genreListForInterstellar);
        movies.add(interstellar);

        String descriptionForMatrix =
                "\"The Matrix\" is a groundbreaking sci-fi action film directed by the Wachowskis, following a computer hacker who discovers the shocking truth about reality and joins a rebellion against sentient machines, blending mind-bending visual effects with philosophical themes of identity and control.";
        List<Genre> genreListForMatrix = new ArrayList<>();
        genreListForMatrix.add(new Genre("ACTION"));
        genreListForMatrix.add(new Genre("SCIENCE_FICTION"));
        Movie matrix = new Movie("The Matrix", descriptionForMatrix, genreListForMatrix);
        movies.add(matrix);

        String descriptionForFightClub = "\"Fight Club\" is a gritty and provocative film that explores the disillusionment of modern masculinity through the lens of underground fight clubs and a nihilistic quest for identity.";
        List<Genre> genreListForFightClub = new ArrayList<>();
        genreListForFightClub.add(new Genre("ACTION"));
        genreListForFightClub.add(new Genre("DRAMA"));
        genreListForFightClub.add(new Genre("THRILLER"));
        Movie fightClub = new Movie("Fight Club", descriptionForFightClub, genreListForFightClub);
        movies.add(fightClub);

        String descriptionForShrek = "\"Shrek\" is a heartwarming animated tale that follows the journey of a grumpy ogre named Shrek as he sets out to rescue Princess Fiona from a tower guarded by a fire-breathing dragon, accompanied by his trusty sidekick Donkey, in a quest that challenges traditional fairy tale tropes and celebrates the beauty of inner character.";
        List<Genre> genreListForShrek = new ArrayList<>();
        genreListForShrek.add(new Genre("ANIMATION"));
        genreListForShrek.add(new Genre("COMEDY"));
        genreListForShrek.add(new Genre("FANTASY"));
        Movie shrek = new Movie("Shrek", descriptionForShrek, genreListForShrek);
        movies.add(shrek);

        String descriptionForCatchMeIfYouCan = "\"Catch me if you can\" is a movie about Frank Abagnale Jr, a con man who poses as a pilot, doctor and a lawyer and cashes forged cheques worth millions before his 21st birthday, despite being constantly chased by FBI agent Carl Hanratty.";
        List<Genre> genreListForCatchMeIfYouCan = new ArrayList<>();
        genreListForCatchMeIfYouCan.add(new Genre("THRILLER"));
        genreListForCatchMeIfYouCan.add(new Genre("COMEDY"));
        genreListForCatchMeIfYouCan.add(new Genre("DRAMA"));
        Movie catchMeIfYouCan = new Movie("Catch Me If You Can", descriptionForCatchMeIfYouCan, genreListForCatchMeIfYouCan);
        movies.add(catchMeIfYouCan);

        String descriptionForAvengers = "\"Avengers\" is a superhero film where members of the titular team unite to save the world from the powerful villain Loki and his alien army.";
        List<Genre> genreListForAvengers = new ArrayList<>();
        genreListForAvengers.add(new Genre("ACTION"));
        genreListForAvengers.add(new Genre("SCIENCE_FICTION"));
        Movie avengers = new Movie("Avengers",descriptionForAvengers, genreListForAvengers);
        movies.add(avengers);

        return movies;
    }
*/
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ").append(id).append("\n");
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
