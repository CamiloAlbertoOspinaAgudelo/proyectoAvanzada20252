package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditHostDTO(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        @Length(max = 300) String photoUrl,
        @Length(max = 300) String descripition,
        @Length(max = 200) String documents
) {
}
