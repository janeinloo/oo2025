package ee.jan.libisevkeskmine.controller;


import ee.jan.libisevkeskmine.entity.Libisev;
import ee.jan.libisevkeskmine.repository.LibisevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibisevController {
    @Autowired
    LibisevRepository libisevRepository;

    @GetMapping("numbers")
    public List<Libisev> getNumbers() { return libisevRepository.findAll(); }

    @PostMapping("numbers")
    public List<Libisev> addNumbers(@RequestBody Libisev libisev) {
        if (libisev.getId() != null) {
            throw new RuntimeException("ERROR_CANNOT_ADD_WITH_ID");
        }
        if (libisev.getNumber() < 0) {
            throw new RuntimeException("ERROR_CANNOT_ADD_WITH_NUMBER");
        }
        libisevRepository.save(libisev);
        return libisevRepository.findAll();
    }

    @GetMapping("numbers/wholesum") //täisarvu summa
    public int getWholeSum() {
        return libisevRepository.findAll().stream().mapToInt(Libisev::getNumber).sum();
    }

    @GetMapping("numbers/aritaverage") //aritmeetilise keskmine
    public double getAritAverage() {
        List<Libisev> numbers = libisevRepository.findAll();
        return numbers.isEmpty() ? 0.0 : numbers.stream().mapToInt(Libisev::getNumber).average().orElse(0.0);
    }

    @GetMapping("numbers/wholebiggest") //kõige suurem leidub täisnumber
    public int getWholeBiggest() {
        return libisevRepository.findAll().stream().mapToInt(Libisev::getNumber).max().
                orElseThrow(() -> new RuntimeException("ERROR_NO_NUMBERS_IN_DATABASE"));
    }

    @GetMapping("numbers/movingaverage")
    public List<Double> getMovingAverage() {
        // Muudame andmebaasi arvud double-tüüpi listiks
        List<Double> numbers = libisevRepository.findAll()
                .stream()
                .map(Libisev::getNumber)
                .mapToDouble(Integer::doubleValue)
                .boxed()
                .toList();

        // Kui numbreid on vähem kui 3, tagastame tühja massiivi
        if (numbers.size() < 3) {
            return new ArrayList<>();
        }

        List<Double> movingAverage = new ArrayList<>();

        // Arvutame libiseva keskmise
        for (int i = 0; i < numbers.size() - 2; i++) {
            double avg = (numbers.get(i) + numbers.get(i + 1) + numbers.get(i + 2)) / 3.0;
            movingAverage.add(avg); // Lisame tulemuse massiivi
        }

        return movingAverage;
    }
}
