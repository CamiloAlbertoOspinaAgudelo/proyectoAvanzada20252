package co.edu.uniquindio.application.model.entity;


import co.edu.uniquindio.application.model.enums.Rol;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String name;
    private String email;
    private Rol rol;
    private String phone;
    private LocalDate dateBirth;
    private String photoUrl;
}
