package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.MetricsDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AccommodationService {

    void create(CreateAccommodationDTO accommodationDTO) throws  Exception;
    AccommodationDTO get(Long id) throws  Exception;
    void update(Long id, EditAccommodationDTO accommodationDTO) throws  Exception;
    void delete(Long id) throws  Exception;
    List<AccommodationDTO> listAll();
    MetricsDTO getMetrics(Long id, LocalDateTime from, LocalDateTime to) throws Exception;
    List<ReviewDTO> getReviews(Long id) throws Exception;
}
