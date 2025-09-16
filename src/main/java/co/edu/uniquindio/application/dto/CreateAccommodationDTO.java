package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateAccommodationDTO(
        @NotBlank String title,
        @Length(max = 300) String description,
        @NotBlank String city,
        @NotBlank String address,
        @NotNull double lat,
        @NotNull double lng,
        @NotNull int capMax,
        @Length(max = 200) String services,
        @NotBlank @Length(max = 300) String photoUrl1
) {
}
