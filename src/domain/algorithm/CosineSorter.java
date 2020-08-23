package domain.algorithm;

import domain.docs.Document;
import domain.docs.Genre;

import java.util.Comparator;

public class CosineSorter implements Comparator<Genre> {

    private final Document document;

    private final SimilarityAlgorithm algorithm;

    public CosineSorter(String documentText, SimilarityAlgorithm algorithm) {
        this.document = new Document(documentText);
        this.algorithm = algorithm;
    }

    @Override
    public int compare(Genre genre1, Genre genre2) {
        double score1 = algorithm.calcSimilarity(document, genre1);
        double score2 = algorithm.calcSimilarity(document, genre2);
        if (score1 == score2) {
            return 0;
        }
        return score1 < score2 ? 1 : -1;
    }

}
