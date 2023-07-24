package team.yellow.docconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByCity_Id(Long cityId, Pageable pageable);

}
