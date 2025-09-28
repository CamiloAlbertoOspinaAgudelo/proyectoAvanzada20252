package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.review.CreateReviewDTO;

public interface ReviewService {
    void create(CreateReviewDTO reviewDTO) throws Exception;
    void response(String id, CreateReviewDTO reviewDTO) throws Exception;
}
