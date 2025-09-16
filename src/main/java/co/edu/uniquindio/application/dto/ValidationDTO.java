package co.edu.uniquindio.application.dto;

public record ValidationDTO<T>(
        String field,
        String defaultMessage
) {
}
