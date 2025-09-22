package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookingRepository extends JpaRepository<Reservation, Long> {
}
