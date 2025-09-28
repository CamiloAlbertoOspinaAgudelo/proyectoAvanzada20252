package co.edu.uniquindio.application.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class PasswordResetCode {
    @Id
    private String id;
    private String code;
    private LocalDateTime sent;
}
