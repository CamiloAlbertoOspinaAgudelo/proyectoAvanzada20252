package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByAccommodation_ID(Long id);
}
