package ee.jan.Decathlon.Service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class DecathlonService {

    private static final Map<String, double[]> EVENT_COEFFICIENTS = Map.of(
            "100m run", new double[]{25.4347, 18, 1.81},
            "long jump", new double[]{0.14354, 220, 1.4},
            "shot put", new double[]{51.39, 1.5, 1.05},
            "high jump", new double[]{0.8465, 75, 1.42},
            "400m run", new double[]{1.53775, 82, 1.81},
            "110m hurdles", new double[]{5.74352, 28.5, 1.92},
            "discus throw", new double[]{12.91, 4, 1.1},
            "pole vault", new double[]{0.2797, 100, 1.35},
            "javelin throw", new double[]{10.14, 7, 1.08},
            "1500m run", new double[]{0.03768, 480, 1.85}
    );

    public int calculatePoints(String event, double performance) {
        if (!EVENT_COEFFICIENTS.containsKey(event.toLowerCase())) {
            throw new IllegalArgumentException("Tundmatu ala: " + event);
        }

        double[] coef = EVENT_COEFFICIENTS.get(event.toLowerCase());
        double A = coef[0], B = coef[1], C = coef[2];

        if (event.equalsIgnoreCase("100m run") || event.equalsIgnoreCase("400m run") ||
                event.equalsIgnoreCase("110m hurdles") || event.equalsIgnoreCase("1500m run")) {
            return (int) (A * Math.pow(B - performance, C));
        } else if (event.equalsIgnoreCase("long jump") || event.equalsIgnoreCase("high jump") ||
                event.equalsIgnoreCase("pole vault")) {
            return (int) (A * Math.pow((performance * 100) - B, C));
        } else {
            return (int) (A * Math.pow(performance - B, C));
        }
    }
}
