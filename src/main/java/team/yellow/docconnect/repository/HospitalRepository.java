package team.yellow.docconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Page<Hospital> findAllByCity_Id(Long cityId, Pageable pageable);

    Page<Hospital> findAllByState_Id(Long cityId, Pageable pageable);
}