package co.edu.uniquindio.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record EditUserDTO(
        @NotBlank @Length(max=100) String name,
        @Length(max = 10) String phone,
        LocalDate dateBirth,
        @Length(max = 300) String photoUrl
) {
}
