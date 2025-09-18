package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateReviewDTO(
        @NotNull int reservationId,
        @NotNull int rating,
        @NotBlank @Length(max = 300) String comment
) {
}
