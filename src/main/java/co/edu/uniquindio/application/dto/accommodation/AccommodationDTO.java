package co.edu.uniquindio.application.dto.accommodation;

public record AccommodationDTO(
        String title,
        String description,
        AddressDTO address,
        String PriceNight,
        int capMax,
        String services,
        String photoUrl1,
        String photoUrl2,
        String photoUrl3,
        String photoUrl4,
        String photoUrl5,
        String photoUrl6,
        String photoUrl7,
        String photoUrl8,
        String photoUrl9,
        String photoUrl10
) {
}
