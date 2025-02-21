package ee.jan.Veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders") // Andmebaasis tuleb nimi "Orders"
public class Order { // ERROR: syntax error at or near "order"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created; // Date importida --> java.util.Date

    @ManyToOne // Personil võib olla mitu tellimust
    private Person person;

    @ManyToMany // kas toode on seotud ühe kindla orderidga, või saan kasutada producte teiste orderitega.
    private List<Product> products; // List importida --> java.util.List

    private double totalSum;

    //personil
    //@OneToMany
    //private List<Address> aadress;
}
