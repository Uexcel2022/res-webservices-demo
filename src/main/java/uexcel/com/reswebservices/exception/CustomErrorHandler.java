package uexcel.com.reswebservices.exception;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value={UserNotFoundException.class})
    public ResponseEntity<ErrorDetails> UserNotFoundException(UserNotFoundException userNotFound, WebRequest webRequest) {
        ErrorDetails errorDetails = new
                ErrorDetails(LocalDateTime.now(), userNotFound.getMessage(), "Not Found", webRequest.getDescription(false));
        return new  ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new
                ErrorDetails(LocalDateTime.now(), Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(),"Not Found", request.getDescription(false));
        return new  ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorDetails> getErrorDetails(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new
                ErrorDetails(LocalDateTime.now(), ex.getMessage(),"Server Error", webRequest.getDescription(false));
        return new  ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
