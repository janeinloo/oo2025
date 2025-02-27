package ee.jan.Decathlon.controller;

import ee.jan.Decathlon.Service.DecathlonService;
import ee.jan.Decathlon.entity.Athlete;
import ee.jan.Decathlon.entity.Result;
import ee.jan.Decathlon.repository.AthleteRepository;
import ee.jan.Decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

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

    @GetMapping("results")
    public List<Result> getResults() {
        return resultRepository.findAll();
    }

    @PostMapping("results")
    public List<Result> addResult(@RequestBody Result result) {
        if (result.getEvent() == null || result.getEvent().isEmpty()) {
            throw new RuntimeException("ERROR_EVENT_MISSING");
        }
        if (result.getScore() <= 0){
            throw new RuntimeException("ERROR_SCORE_MUST_BE_POSITIVE");
        }
        int points = calculatePoints(result.getEvent(), result.getScore());
        if (points <= 0) {
            throw new IllegalArgumentException("ERROR_POINTS_MUST_BE_POSITIVE");
        }
        result.setPoints(points);
        resultRepository.save(result);

        Athlete athlete = athleteRepository.findById(result.getAthlete().getId()).orElse(null);
        if (athlete != null) {
            List<Result> athleteResults = resultRepository.findByAthleteId(athlete.getId());

            int totalPoints = 0;
            for (Result athleteResult : athleteResults) {
                totalPoints += athleteResult.getPoints();
            }

            athlete.setTotalPoints(totalPoints);
            athleteRepository.save(athlete);

            return resultRepository.findAll();
        } else {
            throw new RuntimeException("ERROR_ATHLETE_NOT_FOUND");
        }
    }

    @PutMapping("results")
    public List<Result> editResult(@RequestBody Result result) {
        if (result.getEvent() == null || result.getEvent().isEmpty()) {
            throw new RuntimeException("ERROR_EVENT_MISSING");
        }
        if (result.getScore() <= 0){
            throw new RuntimeException("ERROR_SCORE_MUST_BE_POSITIVE");
        }
        if (result.getPoints() <=0){
            throw new RuntimeException("ERROR_POINTS_MUST_BE_POSITIVE");
        }
        resultRepository.save(result);
        return resultRepository.findAll();
    }

    @DeleteMapping("results/{id}")
    public List<Result> deleteResult(@PathVariable Long id) {
        resultRepository.deleteById(id);
        return resultRepository.findAll();
    }
}
