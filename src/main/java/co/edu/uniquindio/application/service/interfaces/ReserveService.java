package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;

import java.util.List;

public interface ReserveService {
    void create(CreateReserveDTO reserveDTO) throws Exception;
    List<ReserveDTO> listAll();
    ReserveDTO get(Long id) throws Exception;
    void cancel(Long id) throws Exception;
    void sendReminder() throws Exception;
}
