package ee.jan.proovikt2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeID;
    private String type;
    private String description;
}
