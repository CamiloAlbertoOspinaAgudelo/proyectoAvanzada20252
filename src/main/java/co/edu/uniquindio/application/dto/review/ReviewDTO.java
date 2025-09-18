package co.edu.uniquindio.application.dto.review;

public record ReviewDTO(
        int reservationId,
        int rating,
        String comment
) {
}
