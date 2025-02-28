package br.com.xs3.user_api.exception.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDTO {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

}
