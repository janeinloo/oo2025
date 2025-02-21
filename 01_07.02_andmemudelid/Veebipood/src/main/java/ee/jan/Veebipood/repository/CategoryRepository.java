package ee.jan.Veebipood.repository;


import ee.jan.Veebipood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
// lõpus <Category osa on tähtis, sest see on see millisele tabelile läheb.
// CategoryRepository sees on 50+´funktsiooni ja kõik need eksisteerivad JpaRepositorys.
}
