package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team.yellow.docconnect.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailIgnoreCase(String email);

    Boolean existsByEmailIgnoreCase(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.isEmailVerified = TRUE WHERE u.email = ?1")
    void confirmEmail(String email);
}
