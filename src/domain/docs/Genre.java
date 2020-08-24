package domain.docs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Genre extends Text {

    private String genre;

    private Map<String, Document> documents = new ConcurrentHashMap<>();

    public Genre(String genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Gerne title is required");
        }
        this.genre = genre;
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

    @Override
    public String getText() {
        StringBuilder buffer = new StringBuilder();
        documents.values()
            .stream()
            .forEach(doc -> {
                buffer.append(doc.getText());
                buffer.append(" ");
            });
        return buffer.toString();
    }
}
