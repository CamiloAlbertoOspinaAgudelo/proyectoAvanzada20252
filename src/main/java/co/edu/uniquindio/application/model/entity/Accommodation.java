package co.edu.uniquindio.application.model.entity;

import co.edu.uniquindio.application.dto.accommodation.AddressDTO;
import co.edu.uniquindio.application.model.enums.Service;
import co.edu.uniquindio.application.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 300)
    private String description;
    @Embedded
    private Address address;
    @Column(nullable = false)
    private double PriceNight;
    @Column(nullable = false)
    private int capMax;
    @ElementCollection
    private Set<Service> services;
    @Column
    private Status status;

    private List<String> photoUrls;
    @OneToMany(mappedBy = "accommodation")
    private List<Review> reviews;
}
