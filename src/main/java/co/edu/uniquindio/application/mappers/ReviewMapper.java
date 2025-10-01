package co.edu.uniquindio.application.mappers;

import co.edu.uniquindio.application.dto.review.CreateReviewDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;
import co.edu.uniquindio.application.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

    @Mapping(target = "id", expression = "java(Long.valueOf(java.util.UUID.randomUUID().toString()))")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")

    Review toEntity(CreateReviewDTO reviewDTO);

    ReviewDTO toReviewDTO(Review review);
}
