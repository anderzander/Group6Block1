package at.ac.fhcampuswien.fhmdb.builder;

public class MovieApiRequestBuilder {
    private String base;
    private String query;
    private String genre;
    private Object releaseYear;
    private Object ratingFrom;


    public MovieApiRequestBuilder(String base) {
        this.base = base;
    }


    public MovieApiRequestBuilder query(String query) {
        this.query = query;
        return this;
    }

    public MovieApiRequestBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public MovieApiRequestBuilder releaseYear(Object releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public MovieApiRequestBuilder ratingFrom(Object ratingFrom) {
        this.ratingFrom = ratingFrom;
        return this;
    }

    public String build() {

        StringBuilder urlBuilder = new StringBuilder(base);
        boolean isFirstParam = true;

        if (query != null) {
            urlBuilder
                    .append(isFirstParam ? "?" : "&")
                    .append("query=")
                    .append(query);
            isFirstParam = false;
        }
        if (genre != null) {
            urlBuilder
                    .append(isFirstParam ? "?" : "&")
                    .append("genre=")
                    .append(genre);
            isFirstParam = false;
        }
        if (releaseYear != null) {
            urlBuilder
                    .append(isFirstParam ? "?" : "&")
                    .append("releaseYear=")
                    .append(Integer.toString((Integer) releaseYear));
            isFirstParam = false;
        }
        if (ratingFrom != null) {
            urlBuilder
                    .append(isFirstParam ? "?" : "&")
                    .append("ratingFrom=")
                    .append(Double.toString((Double) ratingFrom));
        }

        return urlBuilder.toString();
    }
}
