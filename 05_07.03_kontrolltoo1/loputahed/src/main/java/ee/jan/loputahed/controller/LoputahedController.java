package ee.jan.loputahed.controller;

import ee.jan.loputahed.entity.Loputahed;
import ee.jan.loputahed.entity.ReversedWord;
import ee.jan.loputahed.repository.LoputahedRepository;
import ee.jan.loputahed.repository.ReverseWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class LoputahedController {
    @Autowired
    LoputahedRepository loputahedRepository;

    @Autowired
    ReverseWordRepository reversedWordRepository;

    @GetMapping("words") //Võtan kõik sõnad
    public List<Loputahed> getWords() { return loputahedRepository.findAll(); }

    @GetMapping("words/reversed") //Võtan kõik sõnad
    public List<ReversedWord> getReverseWords() { return reversedWordRepository.findAll(); }

    @PostMapping("words") //Esimene punkt lisan sõna koos veateatega. (ID-ga ei saa lisada või sõna on puudu)
    public List<Loputahed> addWords(@RequestBody Loputahed loputahed) {
        if (loputahed.getId() != null) {
            throw new RuntimeException("ERROR_CANNOT_ADD_WITH_ID");
        }
        if (loputahed.getWord() == null || loputahed.getWord().isBlank()) {
            throw new RuntimeException("ERROR_WORD_MISSING");
        }

        loputahedRepository.save(loputahed);
        return loputahedRepository.findAll();
    }

    @GetMapping("/lastword") //1. Sõnade lõputähed
    public List<String> getLastWord() {
        return loputahedRepository.findAll().stream()// toob sõnad andmebaasist, substring meetodiga võtab sõna viimase tähe, paneb tulemusi listi ja tagastab meile
                .map(l -> l.getWord().substring(l.getWord().length() - 1)) //proovi kt-s olid ka stream jne kasutusel.
                .collect(Collectors.toList());
    }

    @GetMapping("/wordlength") //2. Sõnade pikkused
    public List<Integer> getWordLength() {
        return loputahedRepository.findAll().stream() //toob kõik sõnad, lengthiga sõna pikkus, väljastab vastuse listina sai teha enam-vähem eelmise järgi.
                .map(l-> l.getWord().length())
                .collect(Collectors.toList());
    }

    @GetMapping("/reverse") //3. Sõnad tagurpidi
    public List<String> getReverseWord() {
        return loputahedRepository.findAll().stream()// toob taas kõik sõnad, stringbuilder objekt - siin oli rohkem interneti abi vaja, reverse keerab ümber, paneb listi ja tagastab.
                .map(l-> new StringBuilder(l.getWord()).reverse().toString())
                .collect(Collectors.toList());

    }

    @GetMapping("/common-ending")
    public String getCommonEnding() {
        Map<String, Long> endingFrequencies = loputahedRepository.findAll().stream() //Võtab õik kirjed esialgsest tabelist
                .map(l -> l.getWord().substring(l.getWord().length() - 1)) //võtab viimase tähe samal meetodil nagu esimeses getmappingus
                .collect(Collectors.groupingBy(letter -> letter, Collectors.counting())); //loeb esinemissageduse

        return endingFrequencies.entrySet().stream()
                .max(Map.Entry.comparingByValue()) //Leiab kõige sagedasema tähe.
                .map(Map.Entry::getKey)
                .orElse("NO_WORDS_AVALIABLE");
    }

    @PostMapping("words/reversed")
    public void saveReversedWords(@RequestParam(value = "times", defaultValue = "1") int times) {
        //votab kõik sõnad algses tabelis
        loputahedRepository.findAll().forEach(l -> {
            for (int i = 0; i < times; i++) {
                String reversedWord = new StringBuilder(l.getWord()).reverse().toString();

                ReversedWord reversedWordEntity = new ReversedWord(l.getWord(), reversedWord);

                reversedWordRepository.save(reversedWordEntity);
            }
        });
    }
}
