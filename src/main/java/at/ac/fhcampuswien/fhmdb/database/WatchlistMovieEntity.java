package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "watchlist")
public class WatchlistMovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    public String apiId;

    public WatchlistMovieEntity(){}
    public WatchlistMovieEntity(Movie movie){
        this.apiId = movie.getMovieID();
    }
}
