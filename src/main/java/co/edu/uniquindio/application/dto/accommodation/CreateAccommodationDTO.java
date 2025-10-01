package co.edu.uniquindio.application.dto.accommodation;

import co.edu.uniquindio.application.model.enums.Service;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public record CreateAccommodationDTO(
        @NotBlank String title,
        @Length(max = 300) String description,
        @NotNull AddressDTO address,
        @NotNull double priceNight,
        @NotNull @Min(1) int capMax,
        Set<Service> services,
        List<String> photoUrls
) {
}
