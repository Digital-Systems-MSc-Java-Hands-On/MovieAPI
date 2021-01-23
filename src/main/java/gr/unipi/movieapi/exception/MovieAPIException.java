package gr.unipi.movieapi.exception;

public class MovieAPIException extends Exception {
	public MovieAPIException() {
        super();
    }

    public MovieAPIException(String message) {
        super(message);
    }

    public MovieAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieAPIException(Throwable cause) {
        super(cause);
    }

    protected MovieAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
