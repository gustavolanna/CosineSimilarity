package domain;

import java.util.HashMap;
import java.util.Map;

public class Document {

    private static final String NON_CHAR_REGX = "[^a-zA-Z0-9 \\t]";

    private String text;

    public Document(String text) {
        if (text == null || "".equals(text.trim())) {
            throw new IllegalArgumentException("Document content cannot be empty");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Map<String, Integer> getFrequencies() {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word: getWords()) {
            Integer frequency = frequencies.get(word);
            frequencies.put(word, frequency == null ? 1 : frequency + 1);
        }
        return frequencies;
    }

    private String[] getWords() {
        return text.toLowerCase()
                .replaceAll(NON_CHAR_REGX, "")
                .split("\\s");
    }

    public double getSimilarity(Document document, SimilarityAlgorithm algorithm) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm cannot be null");
        }
        return algorithm.calcSimilarity(document.getFrequencies(), getFrequencies());
    }

}
