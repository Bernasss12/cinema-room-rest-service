package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CinemaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<ErrorResponse> seatNotAvailable(CinemaException e) {
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                e instanceof WrongPasswordException ?
                        HttpStatus.UNAUTHORIZED :
                        HttpStatus.BAD_REQUEST
        );
    }

}
