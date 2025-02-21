package ee.jan.Veebipood.repository;

import ee.jan.Veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// .findAll() --> List<Person>
// .save(Person)
// .findById(Long)
// .deleteById(Long)

public interface PersonRepository extends JpaRepository<Person, Long> {
}
