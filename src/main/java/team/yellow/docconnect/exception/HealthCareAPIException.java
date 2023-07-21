package team.yellow.docconnect.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class HealthCareAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public HealthCareAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}