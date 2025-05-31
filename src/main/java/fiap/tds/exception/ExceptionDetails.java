package fiap.tds.exception;

public record ExceptionDetails(
        String message,
        java.time.LocalDateTime timestamp,
        int status,
        String exception
) {
}
