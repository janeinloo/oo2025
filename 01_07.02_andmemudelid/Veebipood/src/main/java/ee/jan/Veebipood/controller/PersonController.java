package ee.jan.Veebipood.controller;

// Controller - Päringute vastuvõtmiseks (nii suhtleb front-end back-endiga)
// Repository - Andmebaasipäringute valmis tegemiseks (.findAll --> tagastab kõik)
// Entity - andmemudelid, tabelid andmebaasis

import ee.jan.Veebipood.entity.Person;
import ee.jan.Veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController // annab võimekuse API päringuid vastu võtta

public class PersonController {

    @Autowired
    PersonRepository personRepository;

    // front-end peab saatma ID ja parooli
    // TODO: peab saatma emaili ja parooli. Muid väljasid ei küsi (eesnimi, perenimi)
    // TODO: tagastada korralik mudel front-endile tagasi, mitte boolean
    @PostMapping("login")
    public boolean login(@RequestBody Person person) {
        if (person.getId() == null) { // ID on long
            throw new RuntimeException("ERROR_ID_MISSING");
        }
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new RuntimeException("ERROR_PASSWORD_MISSING");
        }
        Person dbPerson = personRepository.findById(person.getId()).orElseThrow();
        if (dbPerson.getPassword().equals(person.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: Ei tagast parast signupi listi inimestest
    @PostMapping("signup")
    public List<Person> signup(@RequestBody Person person) {
        // viga on: {} <-- email === null || {email: " "} <-- email.isBlank()
        if (person.getEmail() == null || person.getEmail().isBlank()) {
            throw new RuntimeException("ERROR_EMAIL_MISSING");
        }
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new RuntimeException("ERROR_PASSWORD_MISSING");
        }
        personRepository.save(person);
        return personRepository.findAll();
    }
}
