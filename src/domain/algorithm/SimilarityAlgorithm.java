package domain.algorithm;

import domain.docs.Text;

public interface SimilarityAlgorithm {

    double calcSimilarity(Text text1, Text text2);

}
