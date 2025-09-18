package co.edu.uniquindio.application.dto.booking;

import java.time.LocalDateTime;

public record ReserveDTO(
        int accomodationId,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        int guests
        ) {
}
