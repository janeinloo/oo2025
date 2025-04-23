package ee.jan.Decathlon.repository;

import ee.jan.Decathlon.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Page<Athlete> findByCountry(String country, Pageable pageable);
}

// kas backendis repo ja controlleri getmapping on õigesti seadistatud country jaoks?