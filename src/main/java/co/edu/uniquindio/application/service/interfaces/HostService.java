package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.user.CreateHostDTO;
import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.dto.user.EditHostDTO;
import co.edu.uniquindio.application.dto.user.HostDTO;
import co.edu.uniquindio.application.model.enums.ReserveStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface HostService {
    void create(CreateHostDTO hostDTO) throws Exception;
    HostDTO get(Long id) throws Exception;
    void update(Long id, EditHostDTO hostDTO) throws Exception;
    void delete(Long id) throws  Exception;
    List<AccommodationDTO> getPlaces(Long id, int page) throws  Exception;
    List<ReserveDTO> listReserves(Long id, String city, ReserveStatus status, LocalDateTime checkIn, LocalDateTime checkOut, int page) throws Exception;
}
