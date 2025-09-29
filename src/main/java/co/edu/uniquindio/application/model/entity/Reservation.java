package co.edu.uniquindio.application.model.entity;

import co.edu.uniquindio.application.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Reservation {
    private String id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int guests;
    private Status status;
}
