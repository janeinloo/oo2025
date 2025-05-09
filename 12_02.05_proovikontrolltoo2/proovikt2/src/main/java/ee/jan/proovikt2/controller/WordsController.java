package ee.jan.proovikt2.controller;

import ee.jan.proovikt2.entity.Words;
import ee.jan.proovikt2.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class WordsController {
    @Autowired
    WordsRepository wordsRepository;

    @GetMapping("words")
    public List<Words> getAllWords() {
        return wordsRepository.findAll();
    }

    @PostMapping("words")
    public List<Words> addWords(@RequestBody Words words) {
        wordsRepository.save(words);
        return wordsRepository.findAll();
    }

    @PutMapping("words")
    public List<Words> editWords(@RequestBody Words words) {
        if (words.getTypeID() == 0) {
            throw new RuntimeException("Wrong id entered");
        }
        Words existingWords = wordsRepository.findById(words.getTypeID())
                .orElseThrow(() -> new RuntimeException("Word not found"));

        existingWords.setType(words.getType());
        existingWords.setDescription(words.getDescription());

        wordsRepository.save(existingWords);
        return wordsRepository.findAll();
    }

    @DeleteMapping("words/{typeID}")
    public List<Words> deleteWords(@PathVariable Long typeID) {
        wordsRepository.deleteById(typeID);
        return wordsRepository.findAll();
    }

}
