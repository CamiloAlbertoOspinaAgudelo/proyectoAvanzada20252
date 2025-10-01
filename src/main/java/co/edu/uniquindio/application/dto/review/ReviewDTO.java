package co.edu.uniquindio.application.dto.review;

import java.time.LocalDateTime;

public record ReviewDTO(
        Long id,
        int rating,
        String comment,
        LocalDateTime createdAt
) {
}
