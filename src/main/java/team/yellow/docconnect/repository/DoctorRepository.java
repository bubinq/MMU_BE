package team.yellow.docconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.yellow.docconnect.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByCity_Id(Long cityId, Pageable pageable);

    @Query("SELECT ds.doctor FROM DoctorSpecialty ds " +
            "WHERE (:specialtyId IS NULL OR ds.specialty.id = :specialtyId) " +
            "AND (:cityId IS NULL OR ds.doctor.city.id = :cityId) " +
            "AND (:name IS NULL OR LOWER(ds.doctor.firstName) LIKE %:name%) OR LOWER(ds.doctor.lastName) LIKE %:name%")
    Page<Doctor> findBySearchParams(Long specialtyId, Long cityId, String name, Pageable pageable);
}

