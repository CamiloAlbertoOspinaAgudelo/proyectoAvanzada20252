package co.edu.uniquindio.application.dto.review;

public record ResponseDTO<T>(
        boolean error,
        T msg
) {
}
