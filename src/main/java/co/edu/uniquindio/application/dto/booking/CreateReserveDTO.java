package co.edu.uniquindio.application.dto.booking;

import co.edu.uniquindio.application.model.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReserveDTO(
        @NotNull String accomodationId,
        @NotNull LocalDateTime checkIn,
        @NotNull LocalDateTime checkOut,
        @NotNull int guests,
        @NotNull Status status
        ) {
}
