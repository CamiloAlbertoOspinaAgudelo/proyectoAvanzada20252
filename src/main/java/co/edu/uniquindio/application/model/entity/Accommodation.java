package co.edu.uniquindio.application.model.entity;

import co.edu.uniquindio.application.dto.accommodation.AddressDTO;
import co.edu.uniquindio.application.model.enums.Service;
import co.edu.uniquindio.application.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private double priceNight;
    @Column(nullable = false)
    private int capMax;
    @ElementCollection
    private Set<Service> services;

    @Enumerated
    private Status status;

    @ElementCollection
    private List<String> images;

    @OneToMany(mappedBy = "accommodation")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(nullable = false)
    private HostProfile host;

    @OneToMany(mappedBy = "accommodation")
    private List<Reservation> reservations;

    @Column(nullable = false)
    private int totalRatings;

    @Column(nullable = false)
    private double avgRating;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
