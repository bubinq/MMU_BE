package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.yellow.docconnect.entity.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
