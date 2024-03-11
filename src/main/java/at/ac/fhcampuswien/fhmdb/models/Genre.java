package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Genre {
    private String genre;


    public Genre(String genre){
        this.genre = genre;
    }
    public String getGenreAsString(){
        return this.genre;
    }

    public static List<Genre> getAllGenres(){
        List<Genre> listOfAllGenres = new ArrayList<>();

        for (allGenres genreName : Genre.allGenres.values()) {
            listOfAllGenres.add(new Genre(genreName.name()));
        }

        return listOfAllGenres;
    }

    enum allGenres{
        ACTION,
        ADVENTURE,
        ANIMATION,
        BIOGRAPHY,
        COMEDY,
        CRIME,
        DRAMA,
        DOCUMENTARY,
        FAMILY,
        FANTASY,
        HISTORY,
        HORROR,
        MUSICAL,
        MYSTERY,
        ROMANCE,
        SCIENCE_FICTION,
        SPORT,
        THRILLER,
        WAR,
        WESTERN
    }
}
