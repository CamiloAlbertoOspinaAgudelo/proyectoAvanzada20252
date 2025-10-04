package co.edu.uniquindio.application.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record EditReviewDTO(
        @NotNull int rating,
        @NotBlank @Length(max = 300) String comment,
        @NotBlank @Length(max = 300) String response
) {
}
