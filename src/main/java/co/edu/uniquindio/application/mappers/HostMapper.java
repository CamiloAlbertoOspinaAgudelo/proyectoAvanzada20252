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
    @Mapping(target = "status", expression = "java(co.edu.uniquindio.application.model.enums.Status.ACTIVE)")
    @Mapping(target = "rol", expression = "java(co.edu.uniquindio.application.model.enums.Rol.HOST)")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    HostProfile toEntity(CreateHostDTO hostDTO);

    HostDTO toHostDTO(HostProfile host);
}
