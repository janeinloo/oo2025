package ee.jan.libisevkeskmine.repository;

import ee.jan.libisevkeskmine.entity.Libisev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibisevRepository extends JpaRepository<Libisev, Long> {
}
