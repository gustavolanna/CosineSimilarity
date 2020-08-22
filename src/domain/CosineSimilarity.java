package domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity implements SimilarityAlgorithm {

    @Override
    public double calcSimilarity(Map<String, Integer> leftVector, Map<String, Integer> rightVector) {
        if (leftVector == null || rightVector == null) {
            throw new IllegalArgumentException("Vectors must not be null");
        }

        final Set<String> intersection = getIntersection(leftVector, rightVector);
        final double dotProduct = dot(leftVector, rightVector, intersection);

        double d1 = 0.0d;
            for (final Integer value : leftVector.values()) {
            d1 += Math.pow(value, 2);
        }
        double d2 = 0.0d;
        for (final Integer value : rightVector.values()) {
            d2 += Math.pow(value, 2);
        }
        double cosineSimilarity;
        if (d1 <= 0.0 || d2 <= 0.0) {
            cosineSimilarity = 0.0;
        } else {
            cosineSimilarity = (double) (dotProduct / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
        }
        return cosineSimilarity;
    }

    private Set<String> getIntersection(Map<String, Integer> leftVector, Map<String, Integer> rightVector) {
        final Set<String> intersection = new HashSet<>(leftVector.keySet());
        intersection.retainAll(rightVector.keySet());
        return intersection;
    }

    private double dot(Map<String, Integer> leftVector, Map<String, Integer> rightVector,  Set<String> intersection) {
        long dotProduct = 0;
        for (final String key : intersection) {
            dotProduct += leftVector.get(key) * rightVector.get(key);
        }
        return dotProduct;
    }
}
