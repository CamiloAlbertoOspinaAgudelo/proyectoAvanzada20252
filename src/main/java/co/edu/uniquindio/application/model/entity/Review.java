package co.edu.uniquindio.application.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @Column(nullable = false)
    private int rating;
    @Lob
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column
    private String response;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Accommodation accommodation;
//
//    @OneToOne
//    @JoinColumn(nullable = false)
//    private Reservation reservation;
}
