package co.edu.uniquindio.application.mappers;

import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.model.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReserveMapper {
    Reservation toEntity(CreateReserveDTO reserveDTO);
    ReserveDTO toReserveDTO(Reservation reservation);
}
