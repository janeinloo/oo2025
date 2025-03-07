package ee.jan.loputahed.repository;

import ee.jan.loputahed.entity.ReversedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReverseWordRepository extends JpaRepository<ReversedWord, Long> {
}
