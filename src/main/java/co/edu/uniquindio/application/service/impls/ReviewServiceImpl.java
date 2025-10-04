package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.review.CreateReviewDTO;
import co.edu.uniquindio.application.dto.review.EditReviewDTO;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.ReviewMapper;
import co.edu.uniquindio.application.model.entity.Review;
import co.edu.uniquindio.application.repositories.ReviewRepository;
import co.edu.uniquindio.application.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public void create(CreateReviewDTO reviewDTO) throws Exception{
        Review review = reviewMapper.toEntity(reviewDTO);
        reviewRepository.save(review);
    }
    @Override
    public void response(Long id, EditReviewDTO reviewDTO) throws Exception{
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()){
            throw new ValueConflictException("La reseña no existe.");
        }
        Review review = reviewOptional.get();
        review.setResponse(reviewDTO.response());
    }
}
