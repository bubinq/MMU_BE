//package team.yellow.docconnect.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import team.yellow.docconnect.entity.DoctorSpecialty;
//
//public interface DoctorSpecialtyRepository  extends JpaRepository<DoctorSpecialty, Long> {
//    Page<DoctorSpecialty> findAllByDoctor_Id (Long doctorId, Pageable pageable);
//    Page<DoctorSpecialty> findAllBySpecialty_Id (Long specialtyId, Pageable pageable);
//
//}
