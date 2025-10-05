package co.edu.uniquindio.application.dto.user;

import co.edu.uniquindio.application.model.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record CreateHostDTO(
        @NotBlank @Length(max=100) String name,
        @Length(max=10) String phone,
        @NotBlank @Email String email,
        @NotBlank @Length(min = 8) String password,
        @Length(max = 300) String photoUrl,
        @NotNull @Past LocalDate dateBirth,
        @NotNull Rol rol,
        @NotBlank @Length(max = 300) String description,
        @Length(max = 200) List<String> documents
) {
}
