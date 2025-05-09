package ee.jan.proovikt2.repository;

import ee.jan.proovikt2.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<Words, Long> {
}
