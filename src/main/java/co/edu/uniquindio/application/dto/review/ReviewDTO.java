package co.edu.uniquindio.application.dto.review;

public record ReviewDTO(
        String reservationId,
        int rating,
        String comment
) {
}
