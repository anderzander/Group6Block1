package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String genreListToString() {
        StringBuilder out = new StringBuilder();
        for (Genre genre : this.genres) {
            out.append(genre.getGenre());
            if (!genres.get(genres.size()-1).equals(genre)){
                out.append(", ");
            }

        }
        return out.toString();
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here

        String descriptionForInterstellar =
                "\"Interstellar\" is a visually stunning and emotionally gripping sci-fi epic that follows astronauts on a perilous journey through a wormhole in search of a new home for humanity, exploring themes of love, sacrifice, and the resilience of the human spirit.";
        List<Genre> genreListForInterstellar = new ArrayList<>();
        genreListForInterstellar.add(new Genre("ACTION"));
        genreListForInterstellar.add(new Genre("SCIENCE_FICTION"));
        Movie interstellar = new Movie("Interstellar", descriptionForInterstellar,  genreListForInterstellar);
        movies.add(interstellar);

        String descriptionForMatrix =
                "\"The Matrix\" is a groundbreaking sci-fi action film directed by the Wachowskis, following a computer hacker who discovers the shocking truth about reality and joins a rebellion against sentient machines, blending mind-bending visual effects with philosophical themes of identity and control.";
        List<Genre> genreListForMatrix = new ArrayList<>();
        genreListForMatrix.add(new Genre("ACTION"));
        genreListForMatrix.add(new Genre("SCIENCE_FICTION"));
        Movie matrix = new Movie("The Matrix", descriptionForMatrix,  genreListForMatrix);
        movies.add(matrix);

        String descriptionForFightClub = "\"Fight Club\" is a gritty and provocative film that explores the disillusionment of modern masculinity through the lens of underground fight clubs and a nihilistic quest for identity.";
        List<Genre> genreListForFightClub = new ArrayList<>();
        genreListForFightClub.add(new Genre("ACTION"));
        genreListForFightClub.add(new Genre("DRAMA"));
        genreListForFightClub.add(new Genre("THRILLER"));
        Movie fightClub = new Movie("Fight Club", descriptionForFightClub, genreListForFightClub);
        movies.add(fightClub);

        String descriptionForShrek ="\"Shrek\" is a heartwarming animated tale that follows the journey of a grumpy ogre named Shrek as he sets out to rescue Princess Fiona from a tower guarded by a fire-breathing dragon, accompanied by his trusty sidekick Donkey, in a quest that challenges traditional fairy tale tropes and celebrates the beauty of inner character.";
        List<Genre> genreListForShrek = new ArrayList<>();
        genreListForShrek.add(new Genre("ANIMATION"));
        genreListForShrek.add(new Genre("COMEDY"));
        genreListForShrek.add(new Genre("FANTASY"));
        Movie shrek = new Movie("Shrek", descriptionForShrek, genreListForShrek);
        movies.add(shrek);

        String descriptionForCatchMeIfYouCan ="\"Catch me if you can\" is a movie about Frank Abagnale Jr, a con man who poses as a pilot, doctor and a lawyer and cashes forged cheques worth millions before his 21st birthday, despite being constantly chased by FBI agent Carl Hanratty.";
        List<Genre> genreListForCatchMeIfYouCan = new ArrayList<>();
        genreListForCatchMeIfYouCan.add(new Genre("THRILLER"));
        genreListForCatchMeIfYouCan.add(new Genre("COMEDY"));
        genreListForCatchMeIfYouCan.add(new Genre("DRAMA"));
        Movie catchMeIfYouCan = new Movie("Catch me if you can", descriptionForCatchMeIfYouCan, genreListForCatchMeIfYouCan);
        movies.add(catchMeIfYouCan);

        String descriptionForAvengers = "\"Avengers\" is a superhero film where members of the titular team unite to save the world from the powerful villain Loki and his alien army.";
        List<Genre> genreListForAvengers = new ArrayList<>();
        genreListForAvengers.add(new Genre("Action"));
        genreListForAvengers.add(new Genre("Science Fiction"));
        Movie avengers = new Movie("Avengers",descriptionForAvengers, genreListForAvengers);
        movies.add(avengers);

        return movies;
    }
}
