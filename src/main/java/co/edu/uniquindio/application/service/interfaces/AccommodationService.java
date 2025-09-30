package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {

    void create(CreateAccommodationDTO accommodationDTO) throws  Exception;
    AccommodationDTO get(String id) throws  Exception;
    void update(String id, EditAccommodationDTO accommodationDTO) throws  Exception;
    void delete(String id) throws  Exception;
    List<AccommodationDTO> listAll();
    void addImage(String id, String image) throws Exception;
    void deleteImage(String id, String imageId) throws Exception;
//    String getMetrics(String id, LocalDate from, LocalDate to) throws Exception;
//    List<ReviewDTO> getReviews(String id) throws Exception;
}
