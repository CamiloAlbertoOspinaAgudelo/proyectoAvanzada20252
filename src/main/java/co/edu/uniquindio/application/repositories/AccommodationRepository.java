package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Accommodation;
import co.edu.uniquindio.application.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {
    @Query("SELECT p FROM Accommodation p WHERE p.title = :title")
    List<Accommodation> getListAccommodations(String title);

//    @Query("""
//    SELECT DISTINCT a
//    FROM Accommodation a
//    WHERE (:city IS NULL OR a.address.city = :city)
//      AND (:priceMin IS NULL OR a.priceNight >= :priceMin)
//      AND (:priceOut IS NULL OR a.priceNight <= :priceOut)
//      AND (
//           :dateIn IS NULL OR :dateOut IS NULL OR
//           NOT EXISTS (
//               SELECT r FROM Reservation r
//               WHERE r.accommodation = a
//               AND (
//                   (r.checkIn <= :dateOut AND r.checkOut >= :dateIn)
//               )
//           )
//      )
//      AND (
//           COALESCE(:services, NULL) IS NULL OR
//           EXISTS (
//               SELECT s FROM a.services s WHERE s IN :services
//           )
//      )
//""")
//    Page<Accommodation> findAllWithFilters(
//            @Param("city") String city,
//            @Param("dateIn") LocalDate dateIn,
//            @Param("dateOut") LocalDate dateOut,
//            @Param("priceMin") Double priceMin,
//            @Param("priceOut") Double priceOut,
//            @Param("services") List<String> services,
//            Pageable pageable
//    );9

    List<Accommodation> findByHost_Id(Long hostId);

}
