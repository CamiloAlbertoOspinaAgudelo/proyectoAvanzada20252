package co.edu.uniquindio.application.dto;

public record ReviewDTO(
        int reservationId,
        int rating,
        String comment
) {
}
