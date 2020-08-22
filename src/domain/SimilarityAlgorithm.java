package domain;

import java.util.Map;

public interface SimilarityAlgorithm {

    double calcSimilarity(Map<String, Integer> leftVector, Map<String, Integer> rightVector);

}
