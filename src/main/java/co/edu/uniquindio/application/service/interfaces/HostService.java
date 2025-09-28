package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;

import java.util.List;

public interface HostService {
    List<AccommodationDTO> getPlaces(String id) throws  Exception;
    List<ReserveDTO> listReserves(String id) throws Exception;
}
