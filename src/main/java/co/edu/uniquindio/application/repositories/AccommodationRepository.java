package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Accommodation;
import co.edu.uniquindio.application.model.entity.Review;
import co.edu.uniquindio.application.model.enums.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {
    @Query("SELECT p FROM Accommodation p WHERE p.title = :title")
    List<Accommodation> getListAccommodations(String title);

    @Query("""
    SELECT a
    FROM Accommodation a
    WHERE (:city IS NULL OR a.address.city = :city)
      AND (:priceMin IS NULL OR a.priceNight >= :priceMin)
      AND (:priceMax IS NULL OR a.priceNight <= :priceMax)
      AND (
           :dateIn IS NULL OR :dateOut IS NULL OR
           NOT EXISTS (
               SELECT r FROM Reservation r
               WHERE r.accommodation = a
               AND (
                   (r.dateTo <= :dateOut AND r.dateFrom >= :dateIn)
               )
           )
      )
      AND (
           COALESCE(:services, NULL) IS NULL OR
           EXISTS (
               SELECT s FROM a.services s WHERE s IN :services
           )
      )
""")
    Page<Accommodation> findAll(String city, LocalDateTime dateIn, LocalDateTime dateOut, Double priceMin, Double priceMax, List<Service> services, Pageable pageable);

    Page<Accommodation> findByHost_Id(Long hostId, Pageable pageable);

}
