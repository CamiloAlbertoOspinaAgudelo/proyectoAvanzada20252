package co.edu.uniquindio.application.dto.booking;

import co.edu.uniquindio.application.model.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReserveDTO(
        @NotNull Long accommodationId,
        @NotNull LocalDateTime dateFrom,
        @NotNull LocalDateTime dateTo,
        @NotNull int guests
        ) {
}
