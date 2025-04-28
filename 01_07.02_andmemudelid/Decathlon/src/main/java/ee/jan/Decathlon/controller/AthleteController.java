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

    @GetMapping("athletes/{id}")
    public Athlete getAthlete(@PathVariable Long id) {
        return athleteRepository.findById(id).orElseThrow();
    }

    @PutMapping("athletes")
    public List<Athlete> editAthlete(@RequestBody Athlete athlete) {
        if (athlete.getId() == 0) {
            throw new RuntimeException("ID must be provided for editing!");
        }
        Athlete existingAthlete = athleteRepository.findById(athlete.getId())
                .orElseThrow(() -> new RuntimeException("Athlete not found!"));

        // Muudame ainult need väljad, mis tulevad kaasa
        existingAthlete.setName(athlete.getName());
        existingAthlete.setCountry(athlete.getCountry());
        existingAthlete.setAge(athlete.getAge());
        existingAthlete.setTotalPoints(athlete.getTotalPoints());

        athleteRepository.save(existingAthlete);
        return athleteRepository.findAll();
    }

    @PatchMapping("athletes")
    public List<Athlete> updateAthlete(@RequestParam Long id, String field, String value) {
        if (id == null) {
            throw new RuntimeException("ERROR_CANNOT_EDIT_WITHOUT_ID");
        }
        Athlete athlete = athleteRepository.findById(id).orElseThrow();

        switch (field) {
            case "name" -> athlete.setName(value);
            case "country" -> athlete.setCountry(value);
            case "age" -> {
                int age = Integer.parseInt(value);
                if (age <= 0) {
                    throw new RuntimeException("ERROR_INVALID_AGE");
                }
                athlete.setAge(age);
            }
            case "totalPoints" -> {
                int points = Integer.parseInt(value);
                if (points < 0) {
                    throw new RuntimeException("ERROR_INVALID_TOTAL_POINTS");
                }
                athlete.setTotalPoints(points);
            }
            default -> throw new RuntimeException("ERROR_UNKNOWN_FIELD");
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
