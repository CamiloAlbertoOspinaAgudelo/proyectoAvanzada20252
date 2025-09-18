package co.edu.uniquindio.application.dto;

import co.edu.uniquindio.application.model.enums.Rol;

import java.time.LocalDate;

public record UserDTO(
        Long id,
        String name,
        String email,
        Rol rol,
        String phone,
        LocalDate dateBirth,
        String photoUrl
) {
}
