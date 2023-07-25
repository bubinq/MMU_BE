package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
