package at.ac.fhcampuswien.fhmdb.exceptions;

public class DatabaseException extends Exception {
    //Public basic no Args. Constructor
    public DatabaseException(){}

    //Standard Constructor
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    //Constructor with only cause
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}

