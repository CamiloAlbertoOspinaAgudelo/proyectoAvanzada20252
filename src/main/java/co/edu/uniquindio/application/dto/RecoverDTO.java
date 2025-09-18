package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecoverDTO(
        @NotBlank @Email String email,
        @NotBlank String code,
        @NotBlank @Length(min = 8) String password
) {
}
