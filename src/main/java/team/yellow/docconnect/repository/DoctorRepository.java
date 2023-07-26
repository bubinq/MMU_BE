package team.yellow.docconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.yellow.docconnect.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByCity_Id(Long cityId, Pageable pageable);

    @Query("SELECT d FROM Doctor d " +
            "WHERE (:specialtyId IS NULL OR d.specialty.id = :specialtyId) " +
            "AND (:cityId IS NULL OR d.city.id = :cityId) " +
            "AND CONCAT(' ', LOWER(d.firstName), ' ', LOWER(d.lastName), ' ') LIKE %:name%")
    Page<Doctor> findBySearchParams(Long specialtyId, Long cityId, String name, Pageable pageable);
}

