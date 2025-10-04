package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.review.CreateReviewDTO;
import co.edu.uniquindio.application.dto.review.EditReviewDTO;

public interface ReviewService {
    void create(CreateReviewDTO reviewDTO) throws Exception;
    void response(Long id, EditReviewDTO reviewDTO) throws Exception;
}
