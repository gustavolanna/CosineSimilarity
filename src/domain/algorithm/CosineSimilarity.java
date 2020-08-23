package domain.algorithm;

import domain.docs.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity implements SimilarityAlgorithm {

    @Override
    public double calcSimilarity(Text text1, Text text2) {
        Map<String, Integer> mapText1 = getFrequencies(text1);
        Map<String, Integer> mapText2 = getFrequencies(text2);

        double similarity = 0;
        double product = getIntersectionProduct(mapText1, mapText2);
        double sumMap1 = calcMapProduct(mapText1);
        if (sumMap1 >= 0.0d) {
            double sumMap2 = calcMapProduct(mapText2);
            if (sumMap2 >= 0.0d) {
                similarity = (product / (Math.sqrt(sumMap1) * Math.sqrt(sumMap2)));
            }
        }
        return similarity;
    }

    private double calcMapProduct(Map<String, Integer> map) {
        double total = 0;
        for (Integer value : map.values()) {
            total += Math.pow(value, 2);
        }
        return total;
    }

    private double getIntersectionProduct(Map<String, Integer> mapText1, Map<String, Integer> mapText2) {
        Set<String> commonKeys = new HashSet<>(mapText1.keySet());
        commonKeys.retainAll(mapText2.keySet());
        double total = 0.0d;
        for (String word: commonKeys) {
            total += mapText1.get(word) * mapText2.get(word);
        }
        return total;
    }

    public Map<String, Integer> getFrequencies(Text text) {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word: text.getWords()) {
            Integer frequency = frequencies.get(word);
            frequencies.put(word, frequency == null ? 1 : frequency + 1);
        }
        return frequencies;
    }

}
