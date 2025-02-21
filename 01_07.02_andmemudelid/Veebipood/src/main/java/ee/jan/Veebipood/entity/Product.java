package ee.jan.Veebipood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Hibernate
// automaatselt tekib andmebaasi tabel, mis on klassi nimega

// boolean
// string
// char

//long ->
//int -> 2.1 miljardit
//short -> 128
//byte -> 32

//float -> .8 kohta
//double -> .16 kohta

//public ei vaja getterit/setterit
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // int
    private String name;
    private double price;
    private String image; // .jpg
    private boolean active;

    //
}

// Kui on väikse tähega:
// long
// char
// double
// boolean
// primitiivsed väärtused. ainult väärtuse hoidmiseks.

// kui on suure tähega:
// Long
// String
// Character
//