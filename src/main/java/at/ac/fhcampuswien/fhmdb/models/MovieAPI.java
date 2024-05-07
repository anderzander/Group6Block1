package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class MovieAPI {

    static final String startURL = "https://prog2.fh-campuswien.ac.at/movies";

    public static String urlForFilteredList(Object releaseYear, Object rating){
        StringBuilder outputURL = new StringBuilder();
        if (releaseYear != null && rating != null){
            return outputURL.append(startURL + "?releaseYear=").append(releaseYear).append("&ratingFrom=").append(rating).toString();
        } else if (releaseYear != null){
            return outputURL.append(startURL + "?releaseYear=").append(releaseYear).toString();
        } else if (rating != null){
            return outputURL.append(startURL + "?ratingFrom=").append(rating).toString();
        }

        return startURL;
    }
    public static List<Movie> getMoviesFromApi(String url) throws IOException {
        URL getMoviesURL = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) getMoviesURL.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("Response CODE: " + responseCode);

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
//            System.out.println(stringBuilder);  // String of everything we get from the API

            Gson gson = new Gson();;
            String json = stringBuilder.toString();
            json = json.replaceAll("'", "`"); // Replace single quotes with two single quotes
            return Arrays.asList(gson.fromJson(json, Movie[].class));
        } else {
            System.out.println("Error in sending a GET request");
            return null;
        }
    }

    public static void printMoviesDetails(List<Movie> movies) {
        if (movies != null && !movies.isEmpty()) {
            System.out.println("Details des ersten Films:");
            for (Movie movie : movies) {
                System.out.println(movie.toString());
            }
        } else {
            System.out.println("Keine Filme gefunden.");
        }
    }

}
