package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
