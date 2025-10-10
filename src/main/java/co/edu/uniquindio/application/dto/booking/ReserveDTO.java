package co.edu.uniquindio.application.dto.booking;

import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.model.enums.Status;

import java.time.LocalDateTime;

public record ReserveDTO(
        Long id,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        int guests,
        ReserveStatus status
        ) {
}
