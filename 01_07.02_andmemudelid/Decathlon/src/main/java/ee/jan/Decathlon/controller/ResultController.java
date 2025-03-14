package ee.jan.Decathlon.controller;

import ee.jan.Decathlon.Service.DecathlonService;
import ee.jan.Decathlon.entity.Athlete;
import ee.jan.Decathlon.entity.Result;
import ee.jan.Decathlon.repository.AthleteRepository;
import ee.jan.Decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

//teeb seosed/sõltuvused
public class ResultController {
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private DecathlonService decathlonService;

    //Punktisumma arvutamine internetist leitud valemi alusel, asub DecathlonService kaustas
    @PostMapping("/calculate")
    public int calculatePoints(@RequestParam String event, @RequestParam double performance) {
        return decathlonService.calculatePoints(event, performance);
    }

    //Kõikide tulemuste saamine
    @GetMapping("results")
    public List<Result> getResults() {
        return resultRepository.findAll();
    }

    //Tulemuste sisestamiseks

    //Erinevad errorid, kui midagi sisestamata.
    @PostMapping("results")
    public List<Result> addResult(@RequestBody Result result) {
        if (result.getEvent() == null || result.getEvent().isEmpty()) {
            throw new RuntimeException("ERROR_EVENT_MISSING");
        }
        if (result.getScore() <= 0){
            throw new RuntimeException("ERROR_SCORE_MUST_BE_POSITIVE");
        }
        int points = calculatePoints(result.getEvent(), result.getScore()); //Arvutab punktid
        if (points <= 0) {
            throw new IllegalArgumentException("ERROR_POINTS_MUST_BE_POSITIVE");
        }
        result.setPoints(points);
        resultRepository.save(result);

        //Otsib sportlase
        Athlete athlete = athleteRepository.findById(result.getAthlete().getId()).orElse(null);
        if (athlete != null) {
            List<Result> athleteResults = resultRepository.findByAthleteId(athlete.getId());

            int totalPoints = 0;
            for (Result athleteResult : athleteResults) {
                totalPoints += athleteResult.getPoints();
            }

            //Summeerib sportlase kõik punktid ja uuendab andmebaasi
            athlete.setTotalPoints(totalPoints);
            athleteRepository.save(athlete);

            return resultRepository.findAll();
        } else {
            throw new RuntimeException("ERROR_ATHLETE_NOT_FOUND");
        }
    }

    //Tulemuste kustutamiseks
    @DeleteMapping("results/{id}")
    public List<Result> deleteResult(@PathVariable Long id) {
        resultRepository.deleteById(id);
        return resultRepository.findAll();
    }
}
