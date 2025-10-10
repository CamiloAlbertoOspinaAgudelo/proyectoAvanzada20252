package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByAccommodation_Id(Long id, Pageable pageble);


}
