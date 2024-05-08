package at.ac.fhcampuswien.fhmdb.exceptions;

public class MovieApiException extends Exception {
    //Public basic no Args. Constructor
    public MovieApiException(){}

    //Standard Constructor
    public MovieApiException(String message, Throwable cause) {
        super(message, cause);
    }

    //Constructor with only cause
    public MovieApiException(Throwable cause) {
        super(cause);
    }
}
