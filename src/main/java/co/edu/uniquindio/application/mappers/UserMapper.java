package co.edu.uniquindio.application.mappers;


import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.dto.user.UserDTO;
import co.edu.uniquindio.application.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "rol", constant = "USER")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    User toEntity(CreateUserDTO userDTO);

    UserDTO toUserDTO(User user);
}
