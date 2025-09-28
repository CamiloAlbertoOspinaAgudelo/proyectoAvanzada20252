package co.edu.uniquindio.application.dto.accommodation;

import co.edu.uniquindio.application.model.enums.Service;

import java.util.List;

public record AccommodationDTO(
        String id,
        String title,
        String description,
        AddressDTO address,
        String PriceNight,
        int capMax,
        List<Service> services,
        List<String> photoUrls
) {
}
