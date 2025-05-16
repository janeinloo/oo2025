package ee.jan.Dictionary.controller;

import ee.jan.Dictionary.entity.Word;
import ee.jan.Dictionary.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class WordController {

    @Autowired
    WordRepository wordRepository;

    @GetMapping("words")
    public List<Word> getWords() {
        return wordRepository.findAll();
    }

    @PostMapping("words")
    public List<Word> addWord(@RequestBody Word word) {
        wordRepository.save(word);
        return wordRepository.findAll();
    }

    @DeleteMapping("words/{id}")
    public List<Word> deleteWord(@PathVariable Long id) {
        wordRepository.deleteById(id);
        return wordRepository.findAll();
    }

    @PutMapping("words")
    public List<Word> updateWord(@RequestBody Word word) {
        wordRepository.save(word);
        return wordRepository.findAll();
    }

    @GetMapping("words/{id}")
    public Word getWord(@PathVariable Long id) {
        return wordRepository.findById(id).orElse(null);
    }

    @GetMapping("words/paginated-words")
    public Page<Word> getPaginatedWords(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
        String[] parts = sort.split(",");
        String sortField = parts[0];
        Sort.Direction direction = parts[1].equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return wordRepository.findAll(pageable);
    }

}
