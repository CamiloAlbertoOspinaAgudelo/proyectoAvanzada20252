package co.edu.uniquindio.application.dto.booking;

import co.edu.uniquindio.application.model.enums.Status;

import java.time.LocalDateTime;

public record ReserveDTO(
        String id,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        int guests,
        Status status
        ) {
}
