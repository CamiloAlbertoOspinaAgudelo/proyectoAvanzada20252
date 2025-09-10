package co.edu.uniquindio.application.dto;

import java.time.LocalDate;
import co.edu.uniquindio.application.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

public record CreateUserDTO (
        @NotBlank @Length(max=100) String name,
        @Length(max=10) String phone,
        @NotBlank @Email String email,
        @NotBlank @Length(min = 8) String password,
        @Length(max = 300) String photoUrl,
        @NotNull @Past LocalDate dateBirth,
        @NotNull Rol rol
) {
}
