package ee.jan.Veebipood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //kui panna entity siis tekib automaatselt andmebaasi tabel
// @Table(name = "Kategooria")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //@ColumnDefault("false")
    //@ColumnDefault("0")
    private boolean active;
    // kui tuleb tagantjärgi muudatus, juba on mingid andmed sees
    // siis tuleb booleani või int või double lisades error
}
