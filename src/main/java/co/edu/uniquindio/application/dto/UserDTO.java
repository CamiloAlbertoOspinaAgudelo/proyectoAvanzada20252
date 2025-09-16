package co.edu.uniquindio.application.dto;

import co.edu.uniquindio.application.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UserDTO(
        String name,
        String email,
        Rol rol,
        String phone,
        LocalDate dateBirth,
        String photoUrl
) {
}
