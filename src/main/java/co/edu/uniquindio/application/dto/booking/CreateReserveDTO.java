package co.edu.uniquindio.application.dto.booking;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReserveDTO(
        @NotNull String accomodationId,
        @NotNull LocalDateTime checkIn,
        @NotNull LocalDateTime checkOut,
        @NotNull int guests
) {
}
