package cinema.exception;

public class UnavailableSeatException extends CinemaException {

    public UnavailableSeatException() {
        super("The ticket has been already purchased!");
    }
}
