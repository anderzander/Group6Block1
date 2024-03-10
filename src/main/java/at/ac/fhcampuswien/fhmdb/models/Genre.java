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
        String[] allGenres ={"ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY","COMEDY",
                "CRIME", "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR",
                "MUSICAL", "MYSTERY", "ROMANCE", "SCIENCE FICTION", "SPORT", "THRILLER", "WAR",
                "WESTERN"};
        for (String genreName : allGenres) {
            listOfAllGenres.add(new Genre(genreName));
        }

        return listOfAllGenres;
    }
}
