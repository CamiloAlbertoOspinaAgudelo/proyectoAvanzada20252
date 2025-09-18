package co.edu.uniquindio.application.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReserveDTO(
        @NotNull int accomodationId,
        @NotNull LocalDateTime checkIn,
        @NotNull LocalDateTime checkOut,
        @NotNull int guests
) {
}
