package ee.jan.Veebipood.repository;


import ee.jan.Veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Repository tagastab ainult kas Product, List<Product>
    // On juba sisse kirjutatud:
    // .findAll() ---> SELECT * FROM products
    // .save() ---> INSERT values() INTO products
    // .deleteById() ---> DELETE FROM products WHERE id=
    // .findById() ---> SELECT product FROM products

    // Jpa Buddy (JPA Designer)
    //File
    //List<Product> find
    Page<Product> findByCategory_Id(Long id, Pageable pageable);
}
