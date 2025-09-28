package co.edu.uniquindio.application.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;

public record CreateReviewDTO(
        @NotBlank String id,
        @NotNull @Min (1) @Max(5) int rating,
        @NotBlank @Length(max = 300) String comment,
        LocalDateTime date
) {
}
