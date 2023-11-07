package cinema.exception;

public class InvalidSeatException extends CinemaException {

    public InvalidSeatException() {
        super("The number of a row or a column is out of bounds!");
    }
}
