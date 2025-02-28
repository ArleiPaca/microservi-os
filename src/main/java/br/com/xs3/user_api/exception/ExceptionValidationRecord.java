package br.com.xs3.user_api.exception;

import org.springframework.http.HttpStatus;

public record ExceptionValidationRecord(HttpStatus statusCodeError, java.util.Map<String, String> messageError) {
}