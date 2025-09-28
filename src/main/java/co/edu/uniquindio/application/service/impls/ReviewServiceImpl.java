package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.review.CreateReviewDTO;
import co.edu.uniquindio.application.mappers.ReviewMapper;
import co.edu.uniquindio.application.model.entity.Review;
import co.edu.uniquindio.application.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final Map<String, Review> reviewStore = new ConcurrentHashMap<>();
    private final ReviewMapper reviewMapper;

    @Override
    public void create(CreateReviewDTO reviewDTO) throws Exception{
        Review review = reviewMapper.toEntity(reviewDTO);
        reviewStore.put(review.getId(), review);
    }
    @Override
    public void response(String id, CreateReviewDTO reviewDTO) throws Exception{
    }
}
