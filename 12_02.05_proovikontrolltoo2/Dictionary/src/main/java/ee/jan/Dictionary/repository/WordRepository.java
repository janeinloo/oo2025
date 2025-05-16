package ee.jan.Dictionary.repository;

import ee.jan.Dictionary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {

}
