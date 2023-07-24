package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}