package ee.jan.hulknurk.controller;

import ee.jan.hulknurk.entity.Hulknurk;
import ee.jan.hulknurk.repository.HulknurkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HulknurkController {
    @Autowired
    HulknurkRepository hulknurkRepository;

    @GetMapping("coordinates")
    public List<Hulknurk> getCoordinates() {
        return hulknurkRepository.findAll();
    }

    @PostMapping("coordinates")
    public List<Hulknurk> addCoordinates(@RequestBody Hulknurk hulknurk) {
        hulknurkRepository.save(hulknurk);
        return hulknurkRepository.findAll();
    }

    @GetMapping("/perimeter")
    public ResponseEntity<Double> getPerimeter() {
        List<Hulknurk> points = hulknurkRepository.findAll();

        if (points.size() < 3){
            throw new RuntimeException("ERROR_NOT_ENOUGH_POINTS_FOR_PERIMETER");
        }

        double perimeter = calculatePerimeter(points);
        return ResponseEntity.ok(perimeter);

    }

    private double calculatePerimeter(List<Hulknurk> points) {
        double perimeter = 0;

        for (int i = 0; i < points.size(); i++) {
            Hulknurk p1 = points.get(i);
            Hulknurk p2 = points.get((i + 1) % points.size());

            double distance = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
            perimeter += distance;
        }

        return perimeter;
    }

    @PutMapping("/increase-x/{amount}")
    public ResponseEntity<String> increaseX(@PathVariable int amount) {
        List<Hulknurk> points = hulknurkRepository.findAll();
        for (Hulknurk p: points) {
            p.setX(p.getX() + amount);
        }
        hulknurkRepository.saveAll(points);
        return ResponseEntity.ok("All X coordinates increased by " + amount);
    }

    @PutMapping("/increase-y/{amount}")
    public ResponseEntity<String> increaseY(@PathVariable int amount) {
        List<Hulknurk> points = hulknurkRepository.findAll();
        for (Hulknurk p: points) {
            p.setY(p.getY() + amount);
        }
        hulknurkRepository.saveAll(points);
        return ResponseEntity.ok("All y coordinates increased by " + amount);
    }
}

