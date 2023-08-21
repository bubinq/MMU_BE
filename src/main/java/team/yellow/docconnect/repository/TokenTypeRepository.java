package team.yellow.docconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.yellow.docconnect.entity.TokenType;

import java.util.Optional;

public interface TokenTypeRepository extends JpaRepository<TokenType, Long> {
    Optional<TokenType> findTokenTypeByName(String name);
}
