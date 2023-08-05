package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailIgnoreCase(String email);

    Boolean existsByEmailIgnoreCase(String email);

}
