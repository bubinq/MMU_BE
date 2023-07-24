package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.City;


public interface CityRepository extends JpaRepository<City, Long> {
}
