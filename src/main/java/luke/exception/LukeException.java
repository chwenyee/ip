package luke.exception;

/**
 * Represents exceptions specific to the Luke application.
 * This Exception is thrown when invalid operations are performed within the application.
 */
public class LukeException extends Exception {

    /**
     * Constructs a LukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public LukeException(String message) {
        super(message);
    }

}

