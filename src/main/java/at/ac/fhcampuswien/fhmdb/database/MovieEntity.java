package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "movie")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    public String movieID;

    @DatabaseField
    public String title;

//    @DatabaseField
//    public List<Genre> genres;
//    @DatabaseField
//    public int releaseYear;
//    @DatabaseField
//    public String description;
//    @DatabaseField
//    public String imgUrl;
//    @DatabaseField
//    public int lengthInMinutes;
//    @DatabaseField
//    public List<String> directors;
//    @DatabaseField
//    public List<String> writers;
//    @DatabaseField
//    public List<String> mainCast;

//    @DatabaseField
//    public double rating;


    public MovieEntity(){}

    public MovieEntity(String movieID, String title) {
        this.movieID = movieID;
        this.title = title;
    }
}
