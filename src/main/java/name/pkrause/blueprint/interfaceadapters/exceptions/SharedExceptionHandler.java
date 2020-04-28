package name.pkrause.blueprint.interfaceadapters.exceptions;

import name.pkrause.blueprint.entities.CatInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Date;

/**  
 * shared exception handler - for transform an exception into a http error "bad_request"
 * @author  Patrick Krause 
 */
@ControllerAdvice
public class SharedExceptionHandler {

    final static Logger LOGGER = LoggerFactory.getLogger(SharedExceptionHandler.class);

    @ExceptionHandler({CatInvalidException.class, CatControllerInvalidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception exception) {
        String errorMsg = exception.getMessage();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(errorMsg)
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date())
                .build();

        LOGGER.error(errorResponse.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(errorMsg)
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date())
                .build();

        LOGGER.error(errorResponse.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCatNotFoundException(CatNotFoundException exception) {

        String errorMsg = exception.getMessage();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(errorMsg)
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(new Date())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
