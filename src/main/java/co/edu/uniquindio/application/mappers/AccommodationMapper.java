package co.edu.uniquindio.application.mappers;


import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.model.entity.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccommodationMapper {

    @Mapping(target = "totalRatings", constant = "0")
    @Mapping(target = "avgRating", constant = "0")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Accommodation toEntity(CreateAccommodationDTO accommodationDTO);
    AccommodationDTO toAccommodationDTO(Accommodation accommodation);
}
