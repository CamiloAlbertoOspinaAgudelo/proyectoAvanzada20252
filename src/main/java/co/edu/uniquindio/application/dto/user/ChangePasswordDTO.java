package co.edu.uniquindio.application.dto.user;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDTO(
        @NotBlank String id,
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
