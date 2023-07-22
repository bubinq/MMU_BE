package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
