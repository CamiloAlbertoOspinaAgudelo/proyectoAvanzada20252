package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.HostProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<HostProfile,Long> {
    Optional<HostProfile> findByUserId(Long userId);

    @Query("select a.host from Accommodation a where a.id = :id ")
    HostProfile findByAccommodation_Id(Long id);
}
