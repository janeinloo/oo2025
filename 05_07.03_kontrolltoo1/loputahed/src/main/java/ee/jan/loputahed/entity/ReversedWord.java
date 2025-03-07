package ee.jan.loputahed.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReversedWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String originalWord;
    private String reversedWord;


    public ReversedWord(String originalWord, String reversedWord) {
        this.originalWord = originalWord;
        this.reversedWord = reversedWord; //This meetod kasutasime ka Jaagupiga c#
    }

    public Long getId() {
        return id;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getReversedWord() {
        return reversedWord;
    }
}
