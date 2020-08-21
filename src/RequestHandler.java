import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Handler for operations related to calculating similarity scores
 *
 */
public class RequestHandler {
	
	/**
	 * Implement for part 1
	 * @param documentText text of the document on which calculate term frequencies
	 * @return a map where the keys are the terms from the document and the value is the frequency of that term.
	 */
	public Map<String, Integer> getTermFrequencies(String documentText) {
		return Collections.emptyMap(); // replace this with the correct map implementation
	}
	
	/**
	 * Implement for part 2
	 * Calculates the similarity score of the documents to each other.
	 * @param doc1Text text of the first document
	 * @param doc2Text text of the second document
	 * @return The similarity score. The range of values will be algorithm specific.
	 */
	public Double getSimilarityScore(String doc1Text, String doc2Text) {
		return 0.0;
	}
	
	/**
	 * Implement for part 3
	 * Add a document to the internal documents which getPopularSimilarity will compare against
	 * @param genre the genre
	 * @param docId
	 * @param documentText
	 */
	public void addDocumentToGenre(String genre, String docId, String documentText) {
		
	}
	
	/**
	 * Implement for part 3
	 * Removes a document from the specified genre
	 * @param genre genre to modify
	 * @param docId document to remove
	 */
	public void removeDocumentFromGenre(String genre, String docId) {
		
	}
	
	/**
	 * Implement for part 3
	 * @param genre
	 * @return list of document ids in a specific genre
	 */
	public List<String> getDocumentsInGenre(String genre) {
		return Collections.emptyList(); // replace this with the correct list implementation
	}
	
	/**
	 * Implement for part 4.
	 * Returns the list of genres which are most similar to the document text specified
	 * @param documentText the text of the document to compare
	 * @param n the number of genres to return in the list
	 * @return list of the closest Genres sorted from most similar genre to least similar
	 */
	public List<String> getNClosestGenres(String documentText, Integer n) {
		return Collections.emptyList(); // replace this with the correct list implementation
	}

}
