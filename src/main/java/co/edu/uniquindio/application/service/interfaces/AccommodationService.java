package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.MetricsDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.model.enums.Service;
import co.edu.uniquindio.application.model.enums.Status;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AccommodationService {

    void create(CreateAccommodationDTO accommodationDTO) throws  Exception;
    AccommodationDTO get(Long id) throws  Exception;
    void update(Long id, EditAccommodationDTO accommodationDTO) throws  Exception;
    void delete(Long id) throws  Exception;
    List<AccommodationDTO> listAll(String city, LocalDateTime dateIn, LocalDateTime dateOut, Double priceMin, Double priceMax, List<Service> services, int page);
    MetricsDTO getMetrics(Long id, LocalDateTime from, LocalDateTime to) throws Exception;
    List<ReviewDTO> getReviews(Long id, int page) throws Exception;
    List<ReserveDTO> getReserves(Long id, LocalDateTime from, LocalDateTime to, ReserveStatus status, int page) throws Exception;
}
