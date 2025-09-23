package co.edu.uniquindio.application.dto.exception;

public record ResponseDTO<T>(
        boolean error,
        T msg
) {
}
