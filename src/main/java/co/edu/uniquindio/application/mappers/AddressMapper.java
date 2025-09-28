package co.edu.uniquindio.application.mappers;


import co.edu.uniquindio.application.dto.accommodation.AddressDTO;
import co.edu.uniquindio.application.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {

    Address toEntity(AddressDTO address);

    AddressDTO toAddressDTO(Address address);
}
