package team.yellow.docconnect.repository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team.yellow.docconnect.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailIgnoreCase(String email);

    Boolean existsByEmailIgnoreCase(String email);

    @Operation(
            summary = "Update User's Confirm Status by Email",
            description = "Update User's Confirm Status by Email is used to update user's confirm status by email"
    )
    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.isVerified = TRUE WHERE u.email = ?1")
    void confirmEmail(String email);
}
