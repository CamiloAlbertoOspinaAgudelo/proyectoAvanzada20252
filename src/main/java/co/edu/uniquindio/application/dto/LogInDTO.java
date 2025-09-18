package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDTO(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
