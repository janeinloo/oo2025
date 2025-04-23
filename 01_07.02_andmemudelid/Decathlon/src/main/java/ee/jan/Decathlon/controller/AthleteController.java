package ee.jan.Decathlon.controller;

import ee.jan.Decathlon.entity.Athlete;
import ee.jan.Decathlon.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
//tehtud CategoryControlleri jne tunnimaterjali näitel
@RestController
public class    AthleteController {
    @Autowired
    AthleteRepository athleteRepository;

    @GetMapping("athletes")
    public List<Athlete> getAthletes() {
        return athleteRepository.findAll(); // Select päring
    }

    //Sportlaste lisamiseks
    @PostMapping("athletes")
    public List<Athlete> addAthlete(@RequestBody Athlete athlete) {
        if (athlete.getName() == null || athlete.getName().isEmpty()) { //intellij väga mõnusalt ise lõpetab tingimusi
            throw new RuntimeException("ERROR_NAME_MISSING");
        }
        if (athlete.getAge() == null) {//The isEmpty() method is only available for String, List, and other collections, but not for Integer.
            throw new RuntimeException("ERROR_AGE_MISSING");
        }
        if (athlete.getCountry() == null || athlete.getCountry().isEmpty()) {
            throw new RuntimeException("ERROR_COUNTRY_MISSING");
        }
        athleteRepository.save(athlete);
        return athleteRepository.findAll();
    }


    //Sportlase kustutamiseks
    @DeleteMapping("athletes/{id}")
    public List<Athlete> deleteAthlete(@PathVariable Long id) {
        athleteRepository.deleteById(id);
        return athleteRepository.findAll();
    }

//    @GetMapping("/athlete-country")
//    public List<Athlete> getAthleteByCountry(@RequestParam String country) {
//        List<Athlete> athletes = athleteRepository.findAll();
//        List<Athlete> filteredAthletes = new ArrayList<>();
//
//        for (Athlete athlete : athletes) {
//            if (athlete.getCountry().equals(country)) {
//                filteredAthletes.add(athlete);
//            }
//        }
//        return filteredAthletes;
//    }

    @GetMapping("/athletes-country")
    public Page<Athlete> getAthleteByCountry(@RequestParam(required = false) String country, Pageable pageable) {
        if (country == null || country.isEmpty()) { //ei tea kas see õige
            return athleteRepository.findAll(pageable);
        }

//        if (country.equals("all")) {
//            return athleteRepository.findAll(pageable);
//        }

        return athleteRepository.findByCountry(country, pageable);
    }
}
