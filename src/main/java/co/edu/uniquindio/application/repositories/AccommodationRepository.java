package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {
    @Query("SELECT p FROM Accommodation p WHERE p.title = :title")
    List<Accommodation> getListAccommodations(String title);
}
