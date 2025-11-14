package co.edu.uniquindio.application.dto.accommodation;

import co.edu.uniquindio.application.model.enums.Service;

import java.util.List;
import java.util.Set;

public record AccommodationDTO(
        Long id,
        String title,
        String description,
        AddressDTO address,
        String priceNight,
        int capMax,
        Set<Service> services,
        List<String> photoUrls,
        double avgRating
) {
}
