package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByAccommodation_Host_Id(@Param("hostId") Long hostId);
}
