package co.edu.uniquindio.application.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String direction;
    @Embedded
    private Location location;
}
