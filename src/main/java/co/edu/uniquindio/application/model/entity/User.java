package co.edu.uniquindio.application.model.entity;


import co.edu.uniquindio.application.model.enums.Rol;
import co.edu.uniquindio.application.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, length = 200)
    private String password;
    @Enumerated
    private Status status;
    @Enumerated
    private Rol rol;
    @Column(length = 10)
    private String phone;
    @Column(nullable = false)
    private LocalDate dateBirth;
    @Column(length = 300)
    private String photoUrl;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
