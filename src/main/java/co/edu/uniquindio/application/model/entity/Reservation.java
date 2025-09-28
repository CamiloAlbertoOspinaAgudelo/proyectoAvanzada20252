package co.edu.uniquindio.application.model.entity;

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
}
