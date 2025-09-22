package co.edu.uniquindio.application.dto.user;

import co.edu.uniquindio.application.model.enums.Rol;

import java.time.LocalDate;
import java.util.List;

public record HostDTO(
        String id,
        String name,
        String email,
        Rol rol,
        String phone,
        LocalDate dateBirth,
        String photoUrl,
        String description,
        List<String> documents
) {
}
