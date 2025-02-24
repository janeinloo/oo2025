package ee.jan.Decathlon.repository;

import ee.jan.Decathlon.entity.Athlete;
import ee.jan.Decathlon.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByAthlete(Athlete athlete);
}
