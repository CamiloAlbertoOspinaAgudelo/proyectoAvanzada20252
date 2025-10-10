package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.dto.accommodation.MetricsDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.entity.Review;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r where r.accommodation.host.id = :id " +
            "and (:city is null or r.accommodation.address.city = :city)" +
            "and (:status is null or :status = r.status)" +
            "and (:checkIn is null or :checIn = r.dateFrom)" +
            "and (:checkOut is null or :checOut = r.dateTo)")
    Page<Reservation> findAllByAccommodation_Host_Id(Long id, String city, ReserveStatus status, LocalDateTime checkIn, LocalDateTime checkOut, Pageable pageable);

    List<Reservation> findAllByStatus(@Param("status")ReserveStatus status);

    @Query("select SIZE(r.accommodation.reservations), r.accommodation.avgRating, r.accommodation.totalRatings from Reservation r where r.accommodation.id = :id " +
            "and (:startDate is null or r.dateFrom >= :startDate ) " +
            "and (:endDate is null or r.dateTo <= :endDate )")
    MetricsDTO getMetrics(Long id, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT r FROM Reservation r where r.accommodation.id = :id " +
            "and (:from is null or r.dateFrom >= :from ) " +
            "and (:to is null or r.dateTo <= :to )" +
            "and (:status is null or :status = r.status)")
    Page<Reservation> findAllByAccommodation_Id(Long id, LocalDateTime from, LocalDateTime to, ReserveStatus status, Pageable pageable);

}
