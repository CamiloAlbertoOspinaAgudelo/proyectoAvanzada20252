package co.edu.uniquindio.application.model.entity;


import co.edu.uniquindio.application.model.enums.Rol;
import co.edu.uniquindio.application.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private Status status;
    private Rol rol;
    private String phone;
    private LocalDate dateBirth;
    private String photoUrl;
    private LocalDateTime createdAt;
}
