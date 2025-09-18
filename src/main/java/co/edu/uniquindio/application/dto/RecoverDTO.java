package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecoverDTO(
        @NotBlank @Email String email
) {
}
