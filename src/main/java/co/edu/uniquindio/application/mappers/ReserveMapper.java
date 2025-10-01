package co.edu.uniquindio.application.mappers;

import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.model.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReserveMapper {
    @Mapping(target = "id", expression = "java(Long.valueOf(java.util.UUID.randomUUID().toString()))")
    Reservation toEntity(CreateReserveDTO reserveDTO);
    ReserveDTO toReserveDTO(Reservation reservation);
}
