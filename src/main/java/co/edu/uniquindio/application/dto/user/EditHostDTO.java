package co.edu.uniquindio.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record EditHostDTO(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        LocalDate dateBirth,
        @Length(max = 300) String photoUrl,
        @Length(max = 300) String description,
        @Length(max = 200) List<String> documents
) {
}
