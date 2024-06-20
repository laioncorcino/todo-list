package com.corcino.todo_list.error.handler;

import com.corcino.todo_list.error.StandardError;
import com.corcino.todo_list.error.ValidationError;
import com.corcino.todo_list.error.exception.BadRequestException;
import com.corcino.todo_list.error.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFound(NotFoundException notFoundException) {
        StandardError error = StandardError.builder()
                .title("Object Not Found Exception. Check documentation")
                .status(HttpStatus.NOT_FOUND.value())
                .errorMessage(notFoundException.getMessage())
                .developerMessage(notFoundException.getClass().getName())
                .dateTime(getDateTime())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> handleNotFound(BadRequestException badRequestException) {
        StandardError error = StandardError.builder()
                .title("Bad Request. Check documentation")
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(badRequestException.getMessage())
                .developerMessage(badRequestException.getClass().getName())
                .dateTime(getDateTime())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleInternalException(Exception exception) {
        StandardError error = StandardError.builder()
                .title("Internal error in server")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .dateTime(getDateTime())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Error.class)
    public ResponseEntity<StandardError> handleInternalError(Error err) {
        StandardError error = StandardError.builder()
                .title("Internal error in server ")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(err.getMessage())
                .developerMessage(err.getClass().getName())
                .dateTime(getDateTime())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

}
