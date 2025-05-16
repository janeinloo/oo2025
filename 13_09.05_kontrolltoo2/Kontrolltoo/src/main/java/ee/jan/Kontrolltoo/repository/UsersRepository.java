package ee.jan.Kontrolltoo.repository;

import ee.jan.Kontrolltoo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsersRepository extends JpaRepository<Users, Long> {
}
