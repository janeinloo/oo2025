package ee.jan.Decathlon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event; //spordiala
    private double score; // Antud ala tulemus nt 100m 9,80
    private int points; // Ala järgi arvutatud kriteeriumiga arvutatud ala tulemus.
    @ManyToOne // Ühel sportlasel
    private Athlete athlete;
}
