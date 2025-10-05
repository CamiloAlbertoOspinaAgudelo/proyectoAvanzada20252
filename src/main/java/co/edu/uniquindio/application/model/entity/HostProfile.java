package co.edu.uniquindio.application.model.entity;

import co.edu.uniquindio.application.dto.user.UserDTO;
import co.edu.uniquindio.application.model.enums.Rol;
import co.edu.uniquindio.application.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HostProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column
    private String description;
    @ElementCollection
    private List<String> documents;
    @OneToMany
    @JoinColumn(nullable = false)
    private List<Accommodation> accommodation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private LocalDateTime createdAt;
}
