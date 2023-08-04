package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.yellow.docconnect.entity.State;
@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
