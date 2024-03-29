package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.yellow.docconnect.entity.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Query(value = "SELECT user_id FROM token where token_value=?1", nativeQuery = true)
    Long findUserIdByToken(String token);


    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2, c.expiresAt = ?2" +
            "WHERE c.token = ?1")
    void updateConfirmedAtAndExpiresAt(String token,
                          LocalDateTime confirmedAt);

    @Query(value="SELECT t.token_value FROM token t WHERE t.user_id = ?1 AND t.token_type_id = ?2 ORDER BY t.created_at DESC LIMIT 1", nativeQuery = true)
    String findLatestTokenByUserIdAndTokenTypeId(Long userId, Long tokenTypeId);
}
