package repository;

import domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenreRepository {

    private Map<String, Genre> repository = new ConcurrentHashMap<>();

    public void add(String genreTitle, String docId, String documentText) {
        Genre genre = repository.get(genreTitle);
        if (genre == null) {
            genre = new Genre(genreTitle);
            repository.put(genreTitle, genre);
        }
        genre.add(docId, documentText);
    }

    public void remove(String genreTitle, String docId) {
        Genre genre = repository.get(genreTitle);
        if (genre != null) {
            genre.remove(docId);
        }
    }

    public List<String> getDocumentsInGenre(String genreTitle) {
        Genre genre = repository.get(genreTitle);
        if (genre != null) {
            return genre.getDocuments();
        }
        return new ArrayList<>();
    }
}
