package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.yellow.docconnect.entity.City;
//
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
