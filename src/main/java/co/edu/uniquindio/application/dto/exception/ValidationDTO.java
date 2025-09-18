package co.edu.uniquindio.application.dto.exception;

public record ValidationDTO<T>(
        String field,
        String defaultMessage
) {
}
