package domain;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Genre {

    private String genre;

    private Map<String, Document> documents;

    public Genre(String genre) {
        this.genre = genre;
        this.documents = new ConcurrentHashMap<>();
    }

    public String getGenre() {
        return genre;
    }

    public void add(String docId, String documentText) {
        documents.put(docId, new Document(documentText));
    }

    public void remove(String docId) {
        documents.remove(docId);
    }

    public List<String> getDocuments() {
        return documents.values()
                .stream()
                .map(doc -> doc.getText())
                .collect(Collectors.toList());
    }
}
