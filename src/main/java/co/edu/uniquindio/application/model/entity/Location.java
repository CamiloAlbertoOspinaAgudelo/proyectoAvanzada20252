package co.edu.uniquindio.application.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Location {
    @Column(nullable = false)
    private double lat;
    @Column(nullable = false)
    private double lng;
}
