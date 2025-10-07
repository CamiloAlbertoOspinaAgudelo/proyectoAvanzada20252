package co.edu.uniquindio.application.repositories;

import co.edu.uniquindio.application.model.entity.PasswordResetCode;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {

    @Query("select p from PasswordResetCode p where p.user.id = :id")
    List<PasswordResetCode> getCodeByUser(Long id, Sort sort);
}
