package uexcel.com.reswebservices.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String desc, String message, String details) {
}
