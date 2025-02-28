package br.com.xs3.user_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public final class UserNotFoundException extends Throwable{
    private final String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
