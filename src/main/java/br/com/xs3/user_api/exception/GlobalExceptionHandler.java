package br.com.xs3.user_api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionValidationRecord> handleUserNotFound(
            UserNotFoundException userNotFoundException) {

        Map<String, String> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.NOT_FOUND));
        errors.put("message",userNotFoundException.getMessage());
        errors.put("timestamp",LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionValidationRecord(HttpStatus.BAD_REQUEST,
                        errors));

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionValidationRecord> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errors.put("timestamp",LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionValidationRecord(HttpStatus.BAD_REQUEST, errors)
        );
    }
}
