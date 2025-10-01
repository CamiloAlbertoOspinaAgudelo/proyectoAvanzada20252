package co.edu.uniquindio.application.model.entity;

import co.edu.uniquindio.application.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dateFrom;
    @Column(nullable = false)
    private LocalDateTime dateTo;
    @Column(nullable = false)
    private int guests;
    @Column(nullable = false)
    private Status status;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Accommodation accommodation;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
