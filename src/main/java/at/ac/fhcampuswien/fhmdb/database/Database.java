package at.ac.fhcampuswien.fhmdb.database;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    public static final String DB_URL = "jdbc:h2:file: ./db/moviesdb";
    public static final String user = "user";
    public static final String password = "pass";

    private static ConnectionSource connectionSource;

    private Dao<MovieEntity, Long> dao;

    private static Database instance;

    private  Database(){
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, MovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void testDB() throws SQLException {
        MovieEntity movie = new MovieEntity("testID", "TestName");
        dao.create(movie);
    }

    public static Database getDatabase(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    private static void createConnectionSource() throws SQLException {

        connectionSource = new JdbcConnectionSource(DB_URL, user, password);
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
    }
}