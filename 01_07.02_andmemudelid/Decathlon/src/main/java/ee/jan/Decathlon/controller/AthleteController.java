package ee.jan.Decathlon.controller;

import ee.jan.Decathlon.entity.Athlete;
import ee.jan.Decathlon.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//tehtud CategoryControlleri jne tunnimaterjali näitel
@RestController
public class AthleteController {
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

    //Sportlase editimiseks
    @PutMapping("athletes")
    public List<Athlete> editAthlete(@RequestBody Athlete athlete) {
        if (athlete.getName() == null || athlete.getName().isEmpty()) {
            throw new RuntimeException("ERROR_NAME_MISSING");
        }
        if (athlete.getAge() == null){
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
}
