import domain.CosineSimilarity;
import domain.CosineSorter;
import domain.Document;
import domain.SimilarityAlgorithm;
import repository.GenreRepository;
import repository.InMemoryGenreRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handler for operations related to calculating similarity scores
 *
 */
public class RequestHandler {

	private GenreRepository repository = new InMemoryGenreRepository();

	private SimilarityAlgorithm algorithm = new CosineSimilarity();
	
	/**
	 * Implement for part 1
	 * @param documentText text of the document on which calculate term frequencies
	 * @return a map where the keys are the terms from the document and the value is the frequency of that term.
	 */
	public Map<String, Integer> getTermFrequencies(String documentText) {
		return ((CosineSimilarity)algorithm).getFrequencies(new Document(documentText));
	}
	
	/**
	 * Implement for part 2
	 * Calculates the similarity score of the documents to each other.
	 * @param doc1Text text of the first document
	 * @param doc2Text text of the second document
	 * @return The similarity score. The range of values will be algorithm specific.
	 */
	public Double getSimilarityScore(String doc1Text, String doc2Text) {
		return algorithm.calcSimilarity(new Document(doc1Text), new Document(doc2Text));
	}
	
	/**
	 * Implement for part 3
	 * Add a document to the internal documents which getPopularSimilarity will compare against
	 * @param genre the genre
	 * @param docId
	 * @param documentText
	 */
	public void addDocumentToGenre(String genre, String docId, String documentText) {
		repository.add(genre, docId, documentText);
	}
	
	/**
	 * Implement for part 3
	 * Removes a document from the specified genre
	 * @param genre genre to modify
	 * @param docId document to remove
	 */
	public void removeDocumentFromGenre(String genre, String docId) {
		repository.remove(genre, docId);
	}
	
	/**
	 * Implement for part 3
	 * @param genre
	 * @return list of document ids in a specific genre
	 */
	public List<String> getDocumentsInGenre(String genre) {
		return repository.getDocumentsInGenre(genre);
	}
	
	/**
	 * Implement for part 4.
	 * Returns the list of genres which are most similar to the document text specified
	 * @param documentText the text of the document to compare
	 * @param n the number of genres to return in the list
	 * @return list of the closest Genres sorted from most similar genre to least similar
	 */
	public List<String> getNClosestGenres(String documentText, Integer n) {
		checkNClosesArguments(documentText, n);
		Document document = new Document(documentText);
		return repository.getGenres()
				.stream()
				.sorted(new CosineSorter(documentText, algorithm))
				.limit(n)
				.filter(genre -> algorithm.calcSimilarity(genre, document) > 0)
				.map(genre -> genre.getGenre())
				.collect(Collectors.toList());
	}

	private void checkNClosesArguments(String documentText, Integer n) {
		if (documentText == null) {
			throw new IllegalArgumentException("A document is required");
		}
		if (n == null) {
			throw new IllegalArgumentException("Count is required");
		}
		if (n <= 0) {
			throw new IllegalArgumentException("A positive count is required");
		}
	}

}
