package co.edu.uniquindio.application.dto.user;

import co.edu.uniquindio.application.model.enums.Rol;

import java.time.LocalDate;
import java.util.Set;

public record UserDTO(
        Long id,
        String name,
        String email,
        Rol rol,
        String password,
        String phone,
        LocalDate dateBirth,
        String photoUrl
) {
}
