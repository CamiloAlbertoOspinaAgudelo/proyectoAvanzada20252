package co.edu.uniquindio.application.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Review {
    private String id;
    private int rating;
    private String comment;
    private LocalDateTime date;
}
