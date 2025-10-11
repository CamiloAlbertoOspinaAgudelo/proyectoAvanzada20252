package co.edu.uniquindio.application.mappers;

import co.edu.uniquindio.application.dto.user.CreateHostDTO;
import co.edu.uniquindio.application.dto.user.HostDTO;
import co.edu.uniquindio.application.model.entity.HostProfile;
import co.edu.uniquindio.application.model.enums.Rol;
import co.edu.uniquindio.application.model.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HostMapper {
    HostProfile toEntity(CreateHostDTO hostDTO);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.rol", target = "rol")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.dateBirth", target = "dateBirth")
    @Mapping(source = "user.photoUrl", target = "photoUrl")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "documents", target = "documents")
    HostDTO toHostDTO(HostProfile host);
}
